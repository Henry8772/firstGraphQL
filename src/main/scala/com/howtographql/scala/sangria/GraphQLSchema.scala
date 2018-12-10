package com.howtographql.scala.sangria

import sangria.schema.{Field, ListType, ObjectType}
import models._
// #
import sangria.schema._
import sangria.macros.derive._

object GraphQLSchema {

  // 1
  val LinkType = ObjectType[Unit, Link](
       "Link",
       fields[Unit, Link](
         Field("hello", StringType, resolve = _.value.hello),
         Field("message", StringType, resolve = _.value.message)
       )
     )

  // 2
  val QueryType = ObjectType(
    "Query",
    fields[MyContext, Unit](
      Field("query", ListType(LinkType), resolve = c => c.ctx.dao.query)
    )
  )

  // 3
  val SchemaDefinition = Schema(QueryType)
}