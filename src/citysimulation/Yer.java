/**
*
* @author Lucas Isaac Cassoma Katalahali | lucas.katalahali@ogr.sakarya.edu.tr
* @since 16/04/2026
* <p>
* Şehir, ilçe ve mahalle gibi yerleşim birimleri için ortak özellikleri 
* ve metodları tanımlayan soyut üst sınıftır.
* </p>
*/

package citysimulation;

public abstract class Yer {
	private String adi;
	private int nufus;
	
	public Yer(String adi) {
		this.adi = adi;
	}
	
	public String getAdi() {
		return adi;
	} 
	
	public int getNufus() {
		return nufus;
	} 
	
	//Nüfus değerini güvenli bir şekilde günceller (negatif değer kontrolü ile)
	public void updateNufus(int nufus) {
        if (nufus >= 0) {
            this.nufus = nufus;
        }
    }
	//Alt sınıflar tarafından özelleştirilmesi gereken nüfus hesaplama metodudur
	public abstract void setNufus();
}
