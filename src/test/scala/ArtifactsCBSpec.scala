import java.io.File

import ArtifactsCB.{BucketCB, IndexCB, PropertiesCB}
import com.fasterxml.jackson.databind.JsonNode
import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json._

import scala.io.BufferedSource

/**
  * Created by jrichiemx on 9/10/16.
  */
class ArtifactsCBSpec extends FlatSpec with Matchers{

  "Artifacts" should  "read a json file" in{
    ArtifactsCB.readJson("/directions.json") equals  JsValue
  }

  it should "parse the json to parse to an object" in{
    ArtifactsCB.jsonToObject(json) equals PropertiesCB
  }

  it should "create buckets" in {
    ArtifactsCB.createBuckets(bucketProperties) equals Boolean
  }


  val bucketProperties: PropertiesCB = PropertiesCB("localhost", "8091", "Administrator", "couchbase", Seq(indexesCB), Seq(bucketsCB) )

  val indexesCB: IndexCB = IndexCB("pricing", "Select * from ")

  val bucketsCB: BucketCB = BucketCB("ERS-Pricing", "pricing1", Array("",""))

  val json: JsValue = Json.obj(
    "host" -> "localhost",
    "port" -> "8091",
    "user" -> "Administrator",
    "password" -> "couchbase",

    "indexes" -> Json.arr(
      Json.obj(
        "name" -> "primaryIndex",
        "index" ->  "select from table",
        "role" -> JsNull
      )
    ),

    "buckets" -> Json.arr(
      Json.obj(
        "name" -> "pricing",
        "password" -> "pricing1",
        "documents" -> Json.arr(
          "",
          ""
      )
    )

    )
  )
}
