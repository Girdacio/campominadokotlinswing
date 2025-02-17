package view

import model.Campo
import java.awt.event.MouseEvent
import java.awt.event.MouseEvent.*
import java.awt.event.MouseListener

class MouseClickListener(
        private val campo: Campo,
        private val onBotaoEsquerdo: (Campo) -> Unit,
        private val onBotaoDireito: (Campo) -> Unit
) : MouseListener {

    override fun mousePressed(e: MouseEvent?) {
        when (e?.button) {
            BUTTON1 -> onBotaoEsquerdo(campo)
            BUTTON2 -> onBotaoDireito(campo)
            BUTTON3 -> onBotaoDireito(campo)
        }
    }

    override fun mouseClicked(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    override fun mouseReleased(e: MouseEvent?) {}
}