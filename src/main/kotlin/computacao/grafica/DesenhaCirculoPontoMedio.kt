package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class DesenhaCirculoPontoMedio(val eixoX: Int, val eixoY: Int, val raio: Int) : JPanel() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        val cx = eixoX / 2
        val cy = eixoY / 2
        var x = 0
        var y = raio
        var d = 1 - raio

        g?.let { graficos ->
            graficos.color = Color.RED
            while (x <= y) {
                g.fillRect(cx + x, cy + y, 2, 2)
                g.fillRect(cx - x, cy + y, 2, 2)
                g.fillRect(cx + x, cy - y, 2, 2)
                g.fillRect(cx - x, cy - y, 2, 2)
                g.fillRect(cx + y, cy + x, 2, 2)
                g.fillRect(cx - y, cy + x, 2, 2)
                g.fillRect(cx + y, cy - x, 2, 2)
                g.fillRect(cx - y, cy - x, 2, 2)

                if(d < 0) {
                    d += 2 * x + 3
                } else {
                    d += 2 * (x - y) + 5
                    y--
                }
                x++
            }
            //graficos.fillOval(cx - raio, cy - raio, 2 * raio, 2 * raio)
        }
    }
}