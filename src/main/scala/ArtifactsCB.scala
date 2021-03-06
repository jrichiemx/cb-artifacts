import java.io.File

import ArtifactsCB.PropertiesCB

import com.couchbase.client.java.{Cluster, CouchbaseCluster}
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode

import scala.io.{BufferedSource, Source}
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

import com.couchbase.client.java.cluster.DefaultBucketSettings
/**
  * Created by jrichiemx on 9/10/16.
  */

class ArtifactsCB()


object ArtifactsCB {
  def createBuckets(propertiesCB: PropertiesCB) = {

    val connection = CouchbaseCluster
      .create(propertiesCB.host)
        .clusterManager(propertiesCB.user, propertiesCB.password)


    propertiesCB.buckets.map( bucket =>
      connection.insertBucket(
          DefaultBucketSettings
            .builder()
            .name(bucket.name)
            .build()
      )
    )


  }

  def jsonToObject(json: JsValue): PropertiesCB = {

    json.validate[PropertiesCB] match {

      case ok: JsSuccess[PropertiesCB] => ok.get

      case e:  JsError => e.get

    }

  }


  def readJson(filename: String): JsValue ={

    Json.parse(Source.fromURL(getClass.getResource(filename)).mkString)

  }




  implicit  val bucketReads: Reads[BucketCB] = (
    (JsPath \ "name").read[String] and
    (JsPath \ "password").read[String] and
    (JsPath \ "documents").read[Seq[String]]
    )(BucketCB.apply _)


  implicit  val indexRead: Reads[IndexCB] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "index").read[String]
    )(IndexCB.apply _)


  implicit  val propertiesReads: Reads[PropertiesCB] = (
      (JsPath \ "host").read[String] and
      (JsPath \ "port").read[String] and
      (JsPath \ "user").read[String] and
      (JsPath \ "password").read[String] and
      (JsPath \ "indexes").read[Seq[IndexCB]] and
      (JsPath \ "buckets").read[Seq[BucketCB]]
    )(PropertiesCB.apply _)


  case class PropertiesCB(host: String = "localhost", port: String = "8091", user: String, password: String, indexes: Seq[IndexCB], buckets: Seq[BucketCB] )

  case class IndexCB(name: String, index: String)

  case class BucketCB(name: String, password: String, documents: Seq[String])

}


