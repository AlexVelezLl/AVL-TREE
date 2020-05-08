/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

/**
 *
 * @author kmmarin
 * @param <E>
 */
public class Nodo<E> {
    private E data;
    private Nodo<E> left;
    private Nodo<E> right;
    /**
     * Factor de equilibrio
     */
    private int fe;

    public Nodo(E data) {
        this.data = data;
        left=right=null;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Nodo<E> getLeft() {
        return left;
    }

    public void setLeft(Nodo<E> left) {
        this.left = left;
    }

    public Nodo<E> getRight() {
        return right;
    }

    public void setRight(Nodo<E> right) {
        this.right = right;
    }
    public int getAltura(){
                        int hIzq = 0;
			int hDer = 0;
			
			if(this.getData()==null){
			  return 0;
                        }
	
	
			if(this.getLeft()!=null){	
				hIzq = this.getLeft().getAltura();
			}else{
				hIzq = 0;
			}
	    	
	    	if(this.getRight()!=null){   
	    		hDer = this.getRight().getAltura();
	    	}else{
	    		hDer = 0;
	    	}
	    	return Math.max(hIzq, hDer) + 1;
    }   
    public int getFactorE(){
			int altDer = 0;
			int altIzq = 0;
			if(this.getRight()!=null){
		    	altDer = this.getRight().getAltura();
		   	}
		   	if(this.getLeft()!=null){		    
		   		altIzq = this.getLeft().getAltura();
		   	}
			return (altDer - altIzq);
    }
    
}
