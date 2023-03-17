package computacao.grafica

import java.awt.Graphics
import javax.swing.JFrame
import kotlin.system.exitProcess

object Menu {

    private var graficos: Graphics? = null
    val jFrame = JFrame()
    fun chamarMenu() {
        do {
            printMenu()
            val opcao = readln().toInt()

            if (opcao == 0) {
                graficos?.clearRect(0, 0, 10000, 10000)
            }

            if (opcao == 1) {
                graficos = inicializaJFrameParametrica()
            }

            if (opcao == 2) {
                graficos = inicializaJFramePontoMedio()
            }

            if (opcao == 3) {
                graficos = inicializaJFrameElipseBresenhan()
            }

            if (opcao == 4) {
                graficos = inicializaJFramePoligonoConcavo()
            }

            if (opcao == 5) {
                exitProcess(5)
            }
        } while (opcao != 5)
    }

    private fun inicializaJFrameParametrica(): Graphics? {
        println("Digite a largura da tela: ")
        val eixoX = readln().toInt()
        println("Digite a altura da tela: ")
        val eixoY = readln().toInt()
        println("Digite o raio: ")
        val raio = readln().toInt()
        jFrame.contentPane.add(DesenhaCirculoParametrica(eixoX, eixoY, raio))
        jFrame.setSize(eixoX, eixoY)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jFrame.isVisible = true
        return jFrame.graphics
    }

    private fun inicializaJFramePontoMedio(): Graphics? {
        println("Digite a largura da tela: ")
        val eixoX = readln().toInt()
        println("Digite a altura da tela: ")
        val eixoY = readln().toInt()
        println("Digite o raio: ")
        val raio = readln().toInt()
        jFrame.contentPane.add(DesenhaCirculoPontoMedio(eixoX, eixoY, raio))
        jFrame.setSize(eixoX, eixoY)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jFrame.isVisible = true
        return jFrame.graphics
    }

    private fun inicializaJFrameElipseBresenhan(): Graphics {
        println("Digite a largura da tela: ")
        val eixoX = readln().toInt()
        println("Digite a altura da tela: ")
        val eixoY = readln().toInt()
        println("Digite o raio no eixo x: ")
        val raioEmX = readln().toInt()
        println("Digite o raio no eixo y: ")
        val raioEmY = readln().toInt()
        jFrame.contentPane.add(DesenhaElipseBresenhan(eixoX, eixoY, raioEmX, raioEmY))
        jFrame.setSize(eixoX, eixoY)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jFrame.isVisible = true
        return jFrame.graphics
    }

    private fun inicializaJFramePoligonoConcavo(): Graphics {
        println("Digite a largura da tela: ")
        val eixoX = readln().toInt()
        println("Digite a altura da tela: ")
        val eixoY = readln().toInt()
        jFrame.contentPane.add(DesenhaPoligonoConcavo())
        jFrame.setSize(eixoX, eixoY)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jFrame.isVisible = true
        return jFrame.graphics
    }

    private fun printMenu() {
        println(
            """ESCOLHA UMA DAS OPCOES ABAIXO:
                [0] LIMPAR TELA
                [1] DESENHAR CIRCULO USANDO A EQUACAO PARAMETRICA
                [2] DESENHAR CIRCULO USANDO A EQUACAO DO PONTO MEDIO
                [3] DESENHAR E PREENCHER ELIPSE USANDO BRESENHAN
                [4] DESENHAR E PREENCHER UM POLIGONO CONCAVO
                [5] SAIR
            """.trimMargin()
        )
    }
}