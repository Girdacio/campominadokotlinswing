package view

import model.Campo
import model.CampoEvento
import java.awt.Color
import java.awt.Color.*
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.SwingUtilities

private val COR_BG_NORMAL = Color(184, 184, 184)
private val COR_BG_MARCACAO = Color(8, 179, 247)
private val COR_BG_EXPLOSAO = Color(189, 66, 68)
private val COR_TXT_VERDE = Color(0, 100, 0)

class BotaoCampo(private val campo: Campo) : JButton() {

    init {
        font = font.deriveFont(Font.BOLD)
        background = COR_BG_NORMAL
        isOpaque = true
        border = BorderFactory.createBevelBorder(0)
        addMouseListener(MouseClickListener(campo, { it.abrir()}, { it.alterarMarcacao() }))
        campo.onEvento(this::aplicarEstilo) // notifica o botão de qualquer evento que aconteça com o campo
    }

    private fun aplicarEstilo(campo: Campo, evento: CampoEvento) {
        when(evento) {
            CampoEvento.EXPLOSAO -> aplicarEstiloExplodido()
            CampoEvento.ABERTURA -> aplicarEstiloAberto()
            CampoEvento.MARCACAO -> aplicarEstiloMarcacao()
            else -> aplicarEstiloPadrao()
        }

        SwingUtilities.invokeLater {
            repaint()
            validate()
        }
    }

    private fun aplicarEstiloPadrao() {
        background = COR_BG_NORMAL
        border = BorderFactory.createBevelBorder(0)
        text = ""
    }

    private fun aplicarEstiloMarcacao() {
        background = COR_BG_MARCACAO
        foreground = BLACK
        text = "M"
    }

    private fun aplicarEstiloAberto() {
        background = COR_BG_NORMAL
        border = BorderFactory.createLineBorder(GRAY)

        foreground = when(campo.qtdeVizinhosMinados) {
            1 -> COR_TXT_VERDE
            2 -> BLUE
            3 -> YELLOW
            4, 5, 6 -> RED
            else -> Color.PINK
        }

        text = if (campo.qtdeVizinhosMinados > 0) campo.qtdeVizinhosMinados.toString() else ""
    }

    private fun aplicarEstiloExplodido() {
        background = COR_BG_EXPLOSAO
        text = "X"
    }
}
