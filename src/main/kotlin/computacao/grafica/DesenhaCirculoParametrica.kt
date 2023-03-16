package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel
import kotlin.math.cos
import kotlin.math.sin

class DesenhaCirculoParametrica(val eixoX: Int, val eixoY: Int, var raio: Int) : JPanel() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let { graficos ->
            graficos.color = Color.BLUE
            desenhaLinha(graficos)
            preencherCirculo(graficos)
        }
    }

    private fun desenhaLinha(graficos: Graphics) {
        val cx = eixoX / 2
        val cy = eixoY / 2
        var theta = 0.0
        while (theta <= 2 * Math.PI) {
            val x = (cx + raio * cos(theta)).toInt()
            val y = (cy + raio * sin(theta)).toInt()
            graficos.drawLine(x, y, x, y)
            theta += 0.01
        }
    }

    private fun preencherCirculo(graficos: Graphics) {
        graficos.color = Color.PINK
        while (raio > 0) {
            raio--
            desenhaLinha(graficos)
        }
    }
}