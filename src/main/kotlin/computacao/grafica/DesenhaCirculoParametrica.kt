package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel
import kotlin.math.cos
import kotlin.math.sin

class DesenhaCirculoParametrica(val eixoX: Int, val eixoY: Int, val raio: Int) : JPanel() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        val cx = eixoX / 2
        val cy = eixoY / 2
        var theta = 0.0

        g?.let { graficos ->
            graficos.color = Color.BLUE
            while (theta <= 2 * Math.PI) {
                val x = (cx + raio * cos(theta)).toInt()
                val y = (cy + raio * sin(theta)).toInt()
                graficos.drawLine(x, y, x, y)
                theta += 0.01
            }
            //graficos.fillOval(cx - raio, cy - raio, 2 * raio, 2 * raio)
        }
    }
}