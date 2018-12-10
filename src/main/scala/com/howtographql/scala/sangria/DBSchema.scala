package com.howtographql.scala.sangria

import slick.jdbc.H2Profile.api._

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.language.postfixOps
import com.howtographql.scala.sangria.models._


object DBSchema {

  /**
    * Load schema and populate sample data withing this Sequence od DBActions
    */


  def createDatabase: DAO = {
    val db = Database.forConfig("h2mem")

    Await.result(db.run(databaseSetup), 10 seconds)

    new DAO(db)

  }

  class LinksTable(tag: Tag) extends Table[Link](tag, "LINKS"){

    def hello = column[String]("HELLO")
    def message = column[String]("MESSAGE")

    def * = (hello, message).mapTo[Link]

  }

  val Links = TableQuery[LinksTable]

  val databaseSetup = DBIO.seq(
    Links.schema.create,

    Links forceInsertAll Seq(
      Link("$hello World !","$message")
      
    )
  )

}
