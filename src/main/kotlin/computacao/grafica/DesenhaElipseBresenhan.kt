package computacao.grafica

import java.awt.Graphics
import javax.swing.JPanel

class DesenhaElipseBresenhan(val eixoX: Int, val eixoY: Int,val raioEmX: Int, val raioEmY: Int) : JPanel() {

    private var ponto: Int = 0
    private var auxPonto: Int = 0
    override fun paint(g: Graphics?) {
        super.paint(g)
        val cx = eixoX / 2
        val cy = eixoY / 2
        var x = 0
        var y = raioEmY

        ponto = raioEmX * raioEmY - raioEmX * raioEmX * raioEmY + raioEmX * raioEmX/4

        while (2* raioEmY * raioEmY * x <= 2 * raioEmX * raioEmX * y) {
            g?.drawLine(cx + x, cy + y, cx + x, cy + y)
            g?.drawLine(cx - x, cy + y, cx - x, cy + y)
            g?.drawLine(cx + x, cy - y, cx + x, cy - y)
            g?.drawLine(cx - x, cy - y, cx - x, cy - y)

            if (ponto < 0) {
                x++
                ponto = ponto + 2 * raioEmY * raioEmY * x + raioEmY * raioEmY
            }
            else {
                x++
                y--
                ponto = ponto + 2 * raioEmY * raioEmY * x - 2 * raioEmX * raioEmX * y - raioEmY * raioEmY
            }
        }

        auxPonto = (raioEmY * raioEmY * (x + 0.5) * (x + 0.5) + raioEmX * raioEmX * (y - 1) * (y - 1) - raioEmX * raioEmX * raioEmY * raioEmY).toInt()

        while (y >= 0) {
            g?.drawLine(cx + x, cy + y, cx + x, cy + y)
            g?.drawLine(cx - x, cy + y, cx - x, cy + y)
            g?.drawLine(cx + x, cy - y, cx + x, cy - y)
            g?.drawLine(cx - x, cy - y, cx - x, cy - y)

            if (auxPonto > 0) {
                y--
                auxPonto = auxPonto - 2 * raioEmX * raioEmX * y + raioEmX * raioEmX
            }
            else {
                x++
                y--
                auxPonto = auxPonto + 2 * raioEmY * raioEmY * x - 2 * raioEmX * raioEmX * y - raioEmX * raioEmX
            }
        }
    }
}

// no codigo de Alber, para preencher e Elipse, ele repete a funcao de desenhar
// porem, antes de iniciar o codigo com o Graphics, ele criar o seguinte for:
// for(int aux=0; aux < raioEmX || aux < raioEmY; raioEmX--, raioEmY --)
// ele chama o preenchimento no menu de forma separada