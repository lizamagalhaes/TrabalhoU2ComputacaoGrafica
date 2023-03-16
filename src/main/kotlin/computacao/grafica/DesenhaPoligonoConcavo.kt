package computacao.grafica

import java.awt.Graphics
import java.awt.Polygon
import java.util.*
import javax.swing.JPanel

class DesenhaPoligonoConcavo(val eixoX: Int, val eixoY: Int) : JPanel() {

    val pontosEmX = listOf(100, 250, 300, 400, 300, 250, 180)
    val pontosEmY = listOf(130, 100, 210, 150, 350, 280, 350)
    private var NumeroDeVertices: Int = 7

    override fun paint(g: Graphics?) {
        super.paint(g)

        for (i in 0 until NumeroDeVertices) {
            val x2: Int = pontosEmX.get(i)
            val y2: Int = pontosEmY.get(i)
            val x3: Int =
                pontosEmX.get((i + 1) % NumeroDeVertices) /* e necessario fazer a divisão para garantir que pecorremos todos os pontos*/
            val y3: Int = pontosEmY.get((i + 1) % NumeroDeVertices)
            g!!.drawLine(x2, y2, x3, y3)
        }
        //pintar(g)
    }

    fun pintar(g: Graphics?) {
        super.paint(g)

        val g = graphics
        val poly = Polygon(
            pontosEmX.toIntArray(),
            pontosEmY.toIntArray(),
            NumeroDeVertices
        ) /*a criação desse objeto e necessaria para defirnimos o y min e o y max*/
        val scan = IntArray(size.width) /*cria uma lista com toda o tamanho da altura da tela*/
        val minY = poly.bounds.y /*pega o y min do poligono*/
        val maxY = minY + poly.bounds.height /*pega 0 y max do poligono*/
        for (y in minY until maxY) { /*responsavel por pecorrer o y min ate o y max*/
            Arrays.fill(scan, 0) /*responsavel por pecorrer a tela*/
            var x1 = Int.MAX_VALUE /*cria um inteiro de valor maximo possivel. Importante para definir o valor de x*/
            for (i in 0 until NumeroDeVertices) {
                val x2: Int = pontosEmY.get(i)
                val y2: Int = pontosEmY.get(i)
                val x3: Int =
                    pontosEmX.get((i + 1) % NumeroDeVertices) /* e necessario fazer a divisão para garantir que pecorremos todos os pontos*/
                val y3: Int = pontosEmY.get((i + 1) % NumeroDeVertices)
                if ((y2 < y && y3 >= y) || (y3 < y && y2 >= y)) { /*verifica o valor de ymin e ymax em relação aos nossos vertices*/
                    val x = x2 + (y - y2) * (x3 - x2) / (y3 - y2) /*calcula o valor de x*/
                    scan[x]++ /*registra a tela*/
                    if (x < x1) {
                        x1 = x
                    } /*verifica o menor ponto de x*/
                }
            }
            var fora = false /*booleano responsavel por verificar se a varredura esta fora ou dentro do poligono*/
            for (x in x1 until size.width) {
                if (scan[x] % 2 == 1) { /*verifica se esta fora da tela*/
                    fora = !fora
                }
                if (fora) {
                    g.drawLine(x, y, x, y)
                }
            }
        }
    }
}