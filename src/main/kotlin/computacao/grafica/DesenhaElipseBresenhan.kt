package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class DesenhaElipseBresenhan(val eixoX: Int, val eixoY: Int, var raioEmX: Int, var raioEmY: Int) : JPanel() {

    private var ponto: Int = 0
    private var auxPonto: Int = 0
    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let { graficos ->
            graficos.color = Color.BLACK
            desenhaElipse(graficos)
            preencheElipse(graficos)
        }
    }

    private fun desenhaElipse(graficos: Graphics) {
        val cx = eixoX / 2
        val cy = eixoY / 2
        var x = 0
        var y = raioEmY

        ponto = raioEmX * raioEmY - raioEmX * raioEmX * raioEmY + raioEmX * raioEmX / 4

        while (2 * raioEmY * raioEmY * x <= 2 * raioEmX * raioEmX * y) {
            graficos.drawLine(cx + x, cy + y, cx + x, cy + y)
            graficos.drawLine(cx - x, cy + y, cx - x, cy + y)
            graficos.drawLine(cx + x, cy - y, cx + x, cy - y)
            graficos.drawLine(cx - x, cy - y, cx - x, cy - y)

            if (ponto < 0) {
                x++
                ponto += 2 * raioEmY * raioEmY * x + raioEmY * raioEmY
            } else {
                x++
                y--
                ponto = ponto + 2 * raioEmY * raioEmY * x - 2 * raioEmX * raioEmX * y - raioEmY * raioEmY
            }
        }

        auxPonto = (raioEmY * raioEmY * (x + 0.5) * (x + 0.5)
                + raioEmX * raioEmX * (y - 1) * (y - 1) - raioEmX * raioEmX * raioEmY * raioEmY).toInt()

        while (y >= 0) {
            graficos.drawLine(cx + x, cy + y, cx + x, cy + y)
            graficos.drawLine(cx - x, cy + y, cx - x, cy + y)
            graficos.drawLine(cx + x, cy - y, cx + x, cy - y)
            graficos.drawLine(cx - x, cy - y, cx - x, cy - y)

            auxPonto = if (auxPonto > 0) {
                y--
                auxPonto - 2 * raioEmX * raioEmX * y + raioEmX * raioEmX
            } else {
                x++
                y--
                auxPonto + 2 * raioEmY * raioEmY * x - 2 * raioEmX * raioEmX * y - raioEmX * raioEmX
            }
        }
    }

    private fun preencheElipse(graficos: Graphics) {
        graficos.color = Color.PINK
        while ((raioEmX > 0).and(raioEmY > 0)) {
            raioEmX--
            raioEmY--
            desenhaElipse(graficos)
        }
    }
}