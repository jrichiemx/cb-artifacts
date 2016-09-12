import java.io.File

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode

import scala.io.{BufferedSource, Source}
import play.api.libs.json._
/**
  * Created by jrichiemx on 9/10/16.
  */
object ArtifactsCB {
  def jsonToObject(json: JsValue) = PropertiesCB {

    PropertiesCB("","", IndexCB[](), )

  }


  def readJson(filename: String): JsValue ={

    Json.parse(Source.fromURL(getClass.getResource(filename)).mkString)

  }
}

case class PropertiesCB(host: String = "localhost", port: String = "8091", indexes: Array[IndexCB], bucket: Array[BucketCB] )

case class IndexCB(name: String, index: String)

case class BucketCB(name: String, password: String, documents: Array[String])

