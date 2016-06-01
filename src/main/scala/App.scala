package rnd

import japgolly.scalajs.react._, vdom.prefix_<^._
import org.scalajs.dom._

import scala.scalajs.js, js.JSApp

object App extends JSApp {
  def main(): Unit = {
    ReactDOM.render(sampleApp(), document.getElementById("node0"))
  }

  val sampleApp = ReactComponentB[Unit]("App")
    .render( dcu =>
      <.div("div: position absolute",
        ^.top:="100px",
        ^.left:="100px",
        ^.padding:="2em",
        ^.backgroundColor:="#dddddd",
        ^.position.absolute,
        ButtonPopup.component()
      )
    )
    .build
}

object ButtonPopup {

  val component = ReactComponentB[Unit]("App2")
    .initialState(State(
      text = "press button",
      flagShowModal = false,
      editText = ""
    ))
    .renderBackend[Backend]
    .build

  case class State(text:String, flagShowModal:Boolean, editText:String)

  class Backend($: BackendScope[Unit,State]) {
    def render(s:State) = <.div(
      <.span("text :",<.b(s.text)),<.br(),
      <.button("change text", ^.onClick-->$.modState(_.copy(flagShowModal = true, editText=""))),
      s.flagShowModal ?= Portal(
        <.div(^.cls:="modal-backdrop"),
        <.div(^.cls:="modal-content",
          "div: modal-content",<.br(),
          <.input.withType("text")(
            ^.value:=s.editText, ^.placeholder:="Enter text",
            ^.onChange ==> {(e:ReactEventI) => {
              val v = e.target.value
              $.modState(_.copy(editText=v))
            }}
          ),
          <.button("save", ^.onClick--> $.modState(s => s.copy(text = s.editText, flagShowModal = false)))
        )
      )
    )
  }
}