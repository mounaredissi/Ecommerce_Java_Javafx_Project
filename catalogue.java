package ecommerce;
import java.util.*;
public  class catalogue implements Map<Object, Object>  {
	
int numCat ; 
int periodeCat;  
Map <categorie,List<produit>> produits;
List <produit> prods;


public catalogue (int numcat ,int periodeCat)
{  
	this.numCat=numcat; 
	this.periodeCat=periodeCat; 
	produits=new HashMap<categorie, List<produit>>();
	prods= new LinkedList<produit>();
}  
public int getNumCat() 
{
	return numCat;
} 
public List GetProds() {
	return prods;
}
public int getPeriodeCat() {
	return periodeCat;
}
public void setNumCat(int numCat)
{ 
	this.numCat=numCat; 
}  
public void setperiodeCat(int periodeCat)
{ 
	this.periodeCat=periodeCat; 
} 
public void ajoutProduit(produit produit) {
	if(prods.isEmpty()) {
		produits.put(produit.getCategorie(), new ArrayList<produit>());
		produits.get(produit.getCategorie()).add(produit);
		prods.add(produit);
		
	}
	else {
		Iterator<produit> iterator= prods.iterator();
		while(iterator.hasNext()) {
			if(iterator.next()==produit) {
				System.out.println("le produit existe deja");
				break;
			}
			else {
				if (!containsKey(produit.getCategorie())) {
					produits.put(produit.getCategorie(), new ArrayList<produit>());
					produits.get(produit.getCategorie()).add(produit);
					prods.add(produit);
				}
				else {
					produits.get(produit.getCategorie()).add(produit);
					prods.add(produit);
				}
			}
	}
	
		
	}
	
}
public void AfficheProduitAvecCategorie(categorie cat) {
	if(containsKey(cat)) {
		for(Map.Entry<categorie, List<produit>> e : produits.entrySet()){
			   for(produit e1 : e.getValue())
			       e1.Out();
			}
	}
}


@Override
public Set<Object> keySet() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Collection<Object> values() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Set<Entry<Object, Object>> entrySet() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public int size() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean containsKey(Object key) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean containsValue(Object value) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Object get(Object key) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object put(Object key, Object value) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object remove(Object key) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void putAll(Map<? extends Object, ? extends Object> m) {
	// TODO Auto-generated method stub
	
}
@Override
public void clear() {
	// TODO Auto-generated method stub
	
}



}
