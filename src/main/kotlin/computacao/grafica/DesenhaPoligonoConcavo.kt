package computacao.grafica

import java.awt.Color
import java.awt.Graphics
import java.awt.Polygon
import javax.swing.JPanel

class DesenhaPoligonoConcavo : JPanel() {
    private var numeroDeVertices: Int = 7
    val pontosEmX = listOf(100, 250, 300, 400, 300, 250, 180)
    val pontosEmY = listOf(130, 100, 210, 150, 350, 280, 350)
    private val vertices = arrayOf(
        Pair(100F, 130F), // Primeiro vértice
        Pair(250F, 100F), // Segundo vértice
        Pair(300F, 210F), // Terceiro vértice
        Pair(400F, 150F), // Quarto vértice
        Pair(300F, 350F), // Quinto vértice
        Pair(250F, 280F), // Sexto vértice
        Pair(180F, 350F) // Sétimo vértice
    )

    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let { graficos ->
            graficos.color = Color.BLACK
            desenhaPoligonoConcavo(graficos)
            preencherPoligono(graficos)
        }
    }

    private fun desenhaPoligonoConcavo(graficos: Graphics) {
        val poly = Polygon(pontosEmX.toIntArray(), pontosEmY.toIntArray(), numeroDeVertices)
        graficos.drawPolygon(poly)
    }

    private fun preencherPoligono(graficos: Graphics) {
        graficos.color = Color.PINK

        // Cria uma lista vazia para armazenar as interseções da linha horizontal com o polígono
        val intersections = mutableListOf<Float>()

        // Percorre cada linha horizontal da imagem
        for (y in 0..height) {

            // Define uma variável para contar o número de interseções da linha com o polígono
            var intersectionsCount = 0

            // Percorre cada aresta do polígono
            for (i in 0..6) {
                val vertex1 = vertices[i]
                val vertex2 = vertices[(i + 1) % 7]

                // Verifica se a aresta intersecta a linha horizontal
                if ((vertex1.second <= y && vertex2.second > y) || (vertex1.second > y && vertex2.second <= y)) {
                    val intersectX =
                        (vertex1.first + (y - vertex1.second) / (vertex2.second - vertex1.second) * (vertex2.first - vertex1.first))
                    intersections.add(intersectX) // Adiciona a interseção na lista
                    intersectionsCount++
                }
            }

            // Ordena a lista de interseções
            intersections.sort()

            // Preenche a área entre as interseções da linha horizontal com o polígono
            for (i in 0 until intersectionsCount step 2) {
                graficos.drawLine(
                    intersections[i].toInt(),
                    y.toFloat().toInt(),
                    intersections[i + 1].toInt(),
                    y.toFloat().toInt()
                )
            }

            // Limpa a lista de interseções
            intersections.clear()
        }
    }
}