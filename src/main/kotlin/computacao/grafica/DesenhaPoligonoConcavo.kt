package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import java.awt.Polygon
import java.util.*
import javax.swing.JPanel

class DesenhaPoligonoConcavo(val eixoX: Int, val eixoY: Int) : JPanel() {

    val pontosEmX = listOf(100, 250, 300, 400, 300, 250, 180)
    val pontosEmY = listOf(130, 100, 210, 150, 350, 280, 350)
    private var numeroDeVertices: Int = 7

    override fun paint(g: Graphics?) {
        super.paint(g)
        repeat(numeroDeVertices) { indice ->
            val x2: Int = pontosEmX[indice]
            val y2: Int = pontosEmY[indice]
            val x3: Int = pontosEmX[(indice + 1) % numeroDeVertices]
            val y3: Int = pontosEmY[(indice + 1) % numeroDeVertices]
            g?.let { graficos ->
                graficos.color = Color.BLACK
                graficos.drawLine(x2, y2, x3, y3)
                preencherPoligonoComRasterizacao(graficos)
            }
        }
    }

    private fun preencherPoligonoComRasterizacao(graficos: Graphics) {
        val poly = Polygon(pontosEmX.toIntArray(), pontosEmY.toIntArray(), numeroDeVertices)
        val scan = IntArray(size.width)
        val minY = poly.bounds.y
        val maxY = minY + poly.bounds.height

        for (y in minY until maxY) {
            Arrays.fill(scan, 0)
            var x1 = Int.MAX_VALUE
            repeat(numeroDeVertices) { indice ->
                val x2: Int = pontosEmY[indice]
                val y2: Int = pontosEmY[indice]
                val x3: Int = pontosEmX[(indice + 1) % numeroDeVertices]
                val y3: Int = pontosEmY[(indice + 1) % numeroDeVertices]

                if ((y in (y2 + 1)..y3) || (y in (y3 + 1)..y2)) {
                    val x = x2 + (y - y2) * (x3 - x2) / (y3 - y2)
                    scan[x]++
                    if (x < x1) {
                        x1 = x
                    }
                }
            }
            var fora = false
            graficos.color = Color.PINK
            for (x in x1 until size.width) {
                if (scan[x] % 2 == 1) {
                    fora = !fora
                }
                if (fora) {
                    graficos.drawLine(x, y, x, y)
                }
            }
        }
    }
}