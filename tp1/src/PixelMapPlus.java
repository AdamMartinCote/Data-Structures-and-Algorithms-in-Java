import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
	
		// compl�ter
		AbstractPixel[][] newImageData = new AbstractPixel[height][width]; 
		
		double sinTheta = java.lang.Math.sin(angleRadian);
		double cosTheta = java.lang.Math.cos(angleRadian);
		int xSource, ySource;
		BWPixel buffer = new BWPixel();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				xSource = (int)((cosTheta*j) + (sinTheta*i) + ((-1*cosTheta*x) + (-1*sinTheta*y) + x));
				ySource = (int)((-1*sinTheta*j) + (cosTheta*i) + ((sinTheta*x) + (-1*cosTheta*y) + y));
				
				if(xSource < 0 || xSource > width || ySource < 0 || ySource > height)
					newImageData[i][j] = buffer;
				else
					newImageData[i][j] = imageData[xSource][ySource];
			}
		}
		imageData = newImageData;
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		// compl�ter
		height = w;
		width = h;
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		for (int i = 0; i < pm.height; i++){
			for (int j = 0; j < pm.width; j++){
				if(row0+i < height && col0 + j < width)
					imageData[row0 + i][col0 + j] = pm.imageData[i][j];
			}
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		if(h < 0 || w < 0) return;
		
		// compl�ter	
		AbstractPixel[][] newImageData = new AbstractPixel[h][w];
		int i,j;		
		for(i = 0; i < h; i++){
			for(j = 0; j < w; j++){
				if(i < height && j < width && i < h  && j < w )
					newImageData[i][j] = imageData[i][j];
				else
					newImageData[i][j] = new BWPixel();
			}
		}
		imageData = newImageData;
		height = h;
		width = w;
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter
		AbstractPixel[][] newImageData = new AbstractPixel[height][width];
		int i, j;
		for (i = 0; i < height; i++){
			for(j = 0; j < width; j++){
				if (i - rowOffset < 0 || i - rowOffset >= height ||
					j - colOffset < 0 || j - colOffset >= width )
					newImageData[i][j] = new BWPixel();
				else
					newImageData[i][j] = imageData[i - rowOffset][j - colOffset];
			}
		}
		imageData = newImageData;		
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// compl�ter
		AbstractPixel[][] newImageData = new AbstractPixel[height][width]; 
		double upperCornerX = (x-width)/(2*zoomFactor);			
		double upperCornerY = (y-height)/(2*zoomFactor);
		
		if(upperCornerX < 0)
			upperCornerX=0;
		else if (upperCornerX > width - width/zoomFactor)
			upperCornerX = width - width/zoomFactor;
		
		if(upperCornerY < 0)
			upperCornerY = 0;
		else if (upperCornerY > height - height/zoomFactor)
			upperCornerY = height - height/zoomFactor;
		
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				newImageData[i][j] = imageData[(int)(upperCornerY+i/zoomFactor)]
											  [(int)(upperCornerX+j/zoomFactor)];
			}
		}
		imageData = newImageData;
	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max 
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel  
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur 
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		// compl�ter
		
	}
}