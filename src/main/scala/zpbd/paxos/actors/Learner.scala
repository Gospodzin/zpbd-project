package zpbd.paxos.actors

import akka.actor.{Actor, Props}

/**
 * Created by gospo on 06.04.15.
 */
class Learner extends Actor {
  override def receive: Receive = ???
}

object Learner {
  val props = Props[Learner]
}