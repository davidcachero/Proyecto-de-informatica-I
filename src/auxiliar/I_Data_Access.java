package auxiliar;

import java.util.HashMap;

import models.Catalog;
import models.Currency;

public interface I_Data_Access {
	
	public HashMap<Float, Currency>  getCurrencyData();
	public HashMap<String, Catalog> getCatalogData();
	public boolean saveCurrency(HashMap<Float, Currency> currency);
	public boolean saveCatalog(HashMap<String, Catalog> catalog);
	

}
