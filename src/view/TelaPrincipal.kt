package view

import model.Tabuleiro
import model.TabuleiroEvento
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.*
import javax.swing.JOptionPane.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    TelaPrincipal()
}

class TelaPrincipal(val qtdeLinhas: Int = 10, val qtdeColunas: Int = 10, val qtdeMinas: Int = 10) : JFrame() {
    private var tabuleiro = Tabuleiro(qtdeLinhas = qtdeLinhas, qtdeColunas = qtdeColunas, qtdeMinas = qtdeMinas)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)

    init {
        tabuleiro.onEvento(this::mostrarResultado) // escuta eventos dentro do tabuleiro
        add(painelTabuleiro)

        setSize(690, 438)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        title = "Campo Minado Kotlin"
        isVisible = true

        jMenuBar = menu()
    }

    private fun mostrarResultado(evento: TabuleiroEvento) {
        SwingUtilities.invokeLater {
            val msg = when(evento) {
                TabuleiroEvento.VITORIA -> "Você ganhou!"
                TabuleiroEvento.DERROTA -> "Você perdeu... :P"
            }

            JOptionPane.showMessageDialog(this, msg)
            tabuleiro.reiniciar()

            painelTabuleiro.repaint()
            painelTabuleiro.validate()
        }
    }

    private fun menu() : JMenuBar {
        val menu = JMenu("Arquivo")
        menu.setMnemonic(KeyEvent.VK_A)

        val menuOpcao = JMenu("Opção")
        menuOpcao.setMnemonic(KeyEvent.VK_O)

        val opcaoSair = JMenuItem("Sair", KeyEvent.VK_S)
        opcaoSair.addActionListener(ActionListener { sair() })

        val opcaoNovo = JMenuItem("Novo", KeyEvent.VK_N)
        opcaoNovo.addActionListener(ActionListener { novo() })

        val opcaoIniciante = JMenuItem("Iniciante", KeyEvent.VK_I)
        opcaoSair.addActionListener(ActionListener { iniciante() })

        val opcaoIntermediario = JMenuItem("Intermediário", KeyEvent.VK_T)
        opcaoSair.addActionListener(ActionListener { intermediario() })

        val opcaoAvancado = JMenuItem("Avançado", KeyEvent.VK_D)
        opcaoSair.addActionListener(ActionListener { avancado() })

        menu.add(opcaoNovo)
        menu.addSeparator()
        menu.add(opcaoSair)

        menuOpcao.add(opcaoIniciante)
        menuOpcao.add(opcaoIntermediario)
        menuOpcao.add(opcaoAvancado)

        val menuBar = JMenuBar()
        menuBar.add(menu)
        menuBar.add(menuOpcao)

        return menuBar
    }

    private fun sair() {
        val resposta = JOptionPane.showOptionDialog(
                this,
                "Deseja sair realmente?",
                "Sair",
                YES_NO_OPTION,
                QUESTION_MESSAGE,
                null,
                null,
                CLOSED_OPTION
        )

        if (resposta == 0) exitProcess(0)
    }

    private fun novo() { tabuleiro.reiniciar() }

    private fun iniciante() {
        // TelaPrincipal(qtdeLinhas = 10, qtdeColunas = 10, qtdeMinas = 10)
    }

    private fun intermediario() {
        // TelaPrincipal(qtdeLinhas = 16, qtdeColunas = 30, qtdeMinas = 20)
    }

    private fun avancado() {
        // TelaPrincipal(qtdeLinhas = 40, qtdeColunas = 40, qtdeMinas = 40)
    }
}