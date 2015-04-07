package zpbd.paxos

import akka.actor.{Props, ActorSystem}
import zpbd.paxos.Paxos.Init
import zpbd.paxos.actors.{Acceptor, Proposer}

/**
 * Created by gospo on 06.04.15.
 */
object App extends App{
  val system = ActorSystem("MyActorSystem")
  //val learner = system.actorOf(Learner.props, "learner")
  val acceptors = {for (i <- 0 until 4) yield system.actorOf(Acceptor.props, name = s"acceptor-$i")}.toSet
  val proposer = system.actorOf(Props(new Proposer(0, acceptors)), "proposer")

  proposer ! Init("Hello")

}
