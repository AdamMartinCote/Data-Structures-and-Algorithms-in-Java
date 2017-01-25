/**
 * Classe de pixel en tons de gris
 * @author :
 * @date : 
 */

public class GrayPixel  extends AbstractPixel 
{
	int pixel; // donnee du pixel
	
	/**
	 * Constructeur par defaut (pixel blanc) // TODO blanc ou noir ???
	 */
	GrayPixel()
	{
		this.pixel = 255;
	}
	
	/**
	 * Constructeur par parametre
	 * @param pixel : valeur du pixel
	 */
	GrayPixel(int pixel)
	{
		// compléter
		this.pixel = pixel;
	}
	
	/**
	 * Renvoie la valeur du pixel
	 */
	public int getPixel()
	{
		return pixel;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param pixel: valeur a assigner 
	 */
	public void setPixel(int pixel)
	{
		this.pixel = pixel;
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// compléter
		boolean newPixel = (pixel > 127) ? false : true;
		return new BWPixel(newPixel);
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		// compléter
		return new GrayPixel(this.pixel);		
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// compléter
		int [] newPixel = {pixel,pixel,pixel};
		return new ColorPixel(newPixel);		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// compléter
		int [] newPixel = {pixel,pixel,pixel,255};
		return new TransparentPixel(newPixel);
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		// compléter
		return new GrayPixel(255 - pixel);
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return ((Integer)(pixel)).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (this.pixel < ((GrayPixel) p).pixel) {
			return -1;
		} else {
			if (this.pixel == ((GrayPixel) p).pixel) {
				return 0;
			} else {
				return 1;
			}
		}
		
	}
}
