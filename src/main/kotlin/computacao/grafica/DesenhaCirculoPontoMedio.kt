package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class DesenhaCirculoPontoMedio(val eixoX: Int, val eixoY: Int, var raio: Int) : JPanel() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let { graficos ->
            graficos.color = Color.BLACK
            desenhaCirculo(graficos)
            preencheCirculo(graficos)
        }
    }

    private fun desenhaCirculo(graficos: Graphics) {
        val cx = eixoX / 2
        val cy = eixoY / 2
        var x = 0
        var y = raio
        var d = 1 - raio
        while (x <= y) {
            graficos.fillRect(cx + x, cy + y, 2, 2)
            graficos.fillRect(cx - x, cy + y, 2, 2)
            graficos.fillRect(cx + x, cy - y, 2, 2)
            graficos.fillRect(cx - x, cy - y, 2, 2)
            graficos.fillRect(cx + y, cy + x, 2, 2)
            graficos.fillRect(cx - y, cy + x, 2, 2)
            graficos.fillRect(cx + y, cy - x, 2, 2)
            graficos.fillRect(cx - y, cy - x, 2, 2)

            if (d < 0) {
                d += 2 * x + 3
            } else {
                d += 2 * (x - y) + 5
                y--
            }
            x++
        }
    }

    private fun preencheCirculo(graficos: Graphics) {
        graficos.color = Color.PINK
        while (raio > 0) {
            raio--
            desenhaCirculo(graficos)
        }
    }
}