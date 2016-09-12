import java.io.File

import ArtifactsCB.PropertiesCB
import com.fasterxml.jackson.databind.JsonNode
import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json.{JsObject, JsString, JsValue}

import scala.io.BufferedSource

/**
  * Created by jrichiemx on 9/10/16.
  */
class ArtifactsCBSpec extends FlatSpec with Matchers{

  "Artifacts" should  "read a json file" in{
    ArtifactsCB.readJson("/directions.json") equals  JsValue
  }

  it should "parse the json to parse to an object" in{
    val jsonTest: JsValue = JsObject(Seq("host" -> JsString("localhost")))
    ArtifactsCB.jsonToObject(jsonTest) equals PropertiesCB
  }
}
