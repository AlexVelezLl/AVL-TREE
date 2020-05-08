/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

/**
 * Árbol binario de búsqueda, los elementos mayores estan a la derecha,
 * @author kmmarin
 */
public class AVL<E> { //AVL
        
    private Nodo<E> root;
    private Comparator<E> f;
    
    public AVL(Comparator<E> f) {
        this.f = f;
        this.root=null;
    }

    public Nodo<E> getRoot() {
        return root;
    }

    public void setRoot(Nodo<E> root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
        return root==null;
    }
 
    public int height(){
        return height(root);
    }
    
    /**
     * Calcula la altura de un nodo
     * @param n
     * @return int
     */
    private int height(Nodo<E> n){
        if(n == null) 
            return 0;
        return 1+ Math.max(height(n.getLeft()),height(n.getRight()));
    }
    
    public int totalNodos(){
        return totalNodos(root);
    }
    
    private int totalNodos(Nodo<E> n){
        if (n==null) 
            return 0;
        return 1 + totalNodos(n.getLeft())+ totalNodos(n.getRight());
    }
    
    public int contarHojas(){
        return contarHojas(root);
    }
    private int contarHojas(Nodo<E> n){
        if(n==null) 
            return 0;
        else if(n.getLeft()==null&& n.getRight()==null){
            return 1;
        }return contarHojas(n.getLeft())+ contarHojas(n.getRight());
    }
    
    public boolean add(E element){
        if(element==null)
            return false;
        
        this.root = add(element,root);
        return true;
    }
    
    private Nodo<E> add(E element, Nodo<E> n){
        if(n == null){
            n = new Nodo<>(element);
        }else if(f.compare(element, n.getData()) > 0){
            n.setRight(add(element,n.getRight()));
        }else if(f.compare(element, n.getData()) < 0){
            n.setLeft(add(element,n.getLeft()));
        }
        return n;
    }
    
    public boolean Insert(E element){
        if(element==null)
            return false;
        this.root = Insert(element,root);
        return true;
    }
      
    private Nodo<E> Insert(E element, Nodo<E> n){        
        if(n == null){
            n = new Nodo<>(element);           
            n.setFe(0);
        }else if(f.compare(element, n.getData())>=0){
            n.setRight(Insert(element,n.getRight()));      
            n.setFe(this.calcularCarga(n));
            if(this.calcularCarga(n) >= 2 && this.calcularCarga(n.getRight()) == -1){
                n = this.rotacionDobleDerecha(n);
                n.setFe(this.calcularCarga(n));
            }else if(this.calcularCarga(n) >= 1 && this.calcularCarga(n.getRight())==2){
                n.setRight(this.rotacionSimpleIzquierda(n.getRight()));
                n.getRight().setFe(this.calcularCarga(n.getRight()));
            }else if(this.calcularCarga(n) >= 2){
                n = this.rotacionSimpleIzquierda(n);
                n.setFe(this.calcularCarga(n));
            }
        }else if(f.compare(element, n.getData())<0){
            n.setLeft(Insert(element,n.getLeft()));
            n.setFe(this.calcularCarga(n));
            if(calcularCarga(n) <= -2&& this.calcularCarga(n.getLeft())== 1){
                n = this.rotacionDobleIzquierda(n);
                n.setFe(this.calcularCarga(n));
            }else if(this.calcularCarga(n) == -1 && this.calcularCarga(n.getLeft())== -2){
                n.setLeft(this.rotacionSimpleDerecha(n.getLeft()));
                n.getLeft().setFe(this.calcularCarga(n.getLeft()));
            }else if(this.calcularCarga(n) == -2){
                n = this.rotacionSimpleDerecha(n);
                n.setFe(this.calcularCarga(n));
            }
        }
        return n;
    }
    
    
    public boolean esDegenerado(){
        return esDegenerado(root);
    }
    private boolean esDegenerado(Nodo<E> p) {
        if(p == null){
            return true;
        }
        else if(p.getLeft()!=null && p.getRight()!=null){
            return false;
        }
        return esDegenerado(p.getLeft())&& esDegenerado(p.getRight());
    }
    
    /**
     * Calcula el Factor de Equilibrio: FE
     * mide la carga del nodo. Puede ser 0, 1, 2, -1, -2
     * @return int
     */
    public int calcularCarga(){
        return calcularCarga(root);
    } 
    private int calcularCarga(Nodo<E> n){
        if(n == null) 
            return -1;
        int Carga = height(n.getRight()) - height(n.getLeft());
        return Carga;
    }
    
    private Nodo<E> searchNodo(E data){
        return searchNodo(data, root);
    }
    private Nodo<E> searchNodo(E data, Nodo<E> raiz){
        
        if(raiz == null)
            return raiz;
        else if(raiz.getData().equals(data))
            return raiz;
        else{
            Nodo<E> l = searchNodo(data, raiz.getLeft());
            return (l != null) ? l: searchNodo(data, raiz.getRight());
        }   
    }
    
    /**
     * Obtiene el nodo padre del nodo pasado por parametro. 
     * @param nodo
     * @param raiz
     * @return Nodo
     */
    private Nodo<E> BuscarPadre(Nodo<E> nodo, Nodo<E> raiz){
        Nodo<E> nodoPapa = raiz;
        if(nodo == null || nodoPapa == null || nodoPapa.getData() == nodo.getData())
            return nodo;
        else if(nodoPapa.getRight() != null && nodoPapa.getLeft() != null){
            if(nodoPapa.getRight().getData() == nodo.getData() || nodoPapa.getLeft().getData() == nodo.getData())
                return nodoPapa;
            else{
                Nodo<E> p = BuscarPadre(nodo, nodoPapa.getRight());
                return (p != null) ? p: BuscarPadre(nodo, nodoPapa.getLeft());
            }
        }
        else if(nodoPapa.getRight() == null && nodoPapa.getLeft() != null){
            if(nodoPapa.getLeft().getData() == nodo.getData())
                return nodoPapa;
            return BuscarPadre(nodo, nodoPapa.getLeft());
        }else if(nodoPapa.getRight() != null && nodoPapa.getLeft() == null){
            if(nodoPapa.getRight().getData() == nodo.getData())
                return nodoPapa;
            return BuscarPadre(nodo, nodoPapa.getRight());
        }
        return null;
    }
        
    /**
     * Realiza la operacion de rotacion simple derecha en el subarbol .
     * Se ingresa el nodo que tiene carga 2
     * @param nodo raiz del subarbol a rotar.
     * @return Nodo que pasa a ser la raiz del subarbol estructurado.
     */
    public Nodo<E> rotacionSimpleIzquierda(Nodo<E> nodo){
    	if(nodo == null)
            return null;
        Nodo<E> nodoTemp;
        
        Nodo<E> nodoPapa = nodo.getRight();
        nodo.setRight(null);
        if(nodoPapa.getLeft() != null){
           nodoTemp = nodoPapa.getLeft();
           nodoPapa.setLeft(null);
           nodo.setRight(nodoTemp);
        }
        nodoPapa.setLeft(nodo);
        nodoPapa.getRight().setFe(this.calcularCarga(nodoPapa.getRight()));
        nodoPapa.getLeft().setFe(this.calcularCarga(nodoPapa.getLeft()));
        return nodoPapa;
    }
    
    /**
    * Realiza la operacion de rotacion simple izquierda en el subarbol 
    * que tiene como raiz el nodo pasado por parametro.
    * @param nodo raiz del subarbol a rotar.
    *
    * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
    */
    public Nodo<E> rotacionSimpleDerecha(Nodo<E> nodo){
    	if(nodo == null)
            return null;
        Nodo<E> nodoTemp;
        
        Nodo<E> nodoPapa = nodo.getLeft();
        nodo.setLeft(null);
        if(nodoPapa.getRight() != null){
           nodoTemp = nodoPapa.getRight();
           nodoPapa.setRight(null);
           nodo.setLeft(nodoTemp);
        }
        nodoPapa.setRight(nodo);   
        nodoPapa.getRight().setFe(this.calcularCarga(nodoPapa.getRight()));
        nodoPapa.getLeft().setFe(this.calcularCarga(nodoPapa.getLeft()));
        return nodoPapa;
    }
    
    public Nodo<E> rotacionDobleIzquierda(Nodo<E> nodo){
        if(nodo == null)
            return null;
        Nodo<E> nodoTemp;
        
        Nodo<E> nodoPapa = nodo.getLeft().getRight();
        nodoTemp = nodo.getLeft();
        nodo.setLeft(null);
        nodoTemp.setRight(null);
        nodoPapa.setLeft(nodoTemp);
        nodoPapa.setRight(nodo);   
       
        return nodoPapa;    
    }
    
    public Nodo<E> rotacionDobleDerecha(Nodo<E> nodo){       
        if(nodo == null)
            return null;
        Nodo<E> nodoTemp;
        
        Nodo<E> nodoPapa = nodo.getRight().getLeft();
        nodoTemp = nodo.getRight();
        nodo.setRight(null);
        nodoTemp.setLeft(null);
        nodoPapa.setRight(nodoTemp);
        nodoPapa.setLeft(nodo);        
        return nodoPapa;        
    }
    
    public E max(){
        return max(root);
    }
    private E max(Nodo<E> n){
        if(n==null)return null;
        else if(n.getRight()==null){
            return n.getData();
        }else
            return max(n.getRight());
    }
    
    public E min(){
        return min(root);
    }
    private E min(Nodo<E> n){
        if(n==null)return null;
        else if(n.getLeft()==null){
            return n.getData();
        }else
            return min(n.getLeft());
    }
     
    public boolean contains(E element){
        if(element==null|| isEmpty()) return false;
        return contains(element,root);
         
    }
    
    private boolean contains(E element, Nodo<E> n){
        if(n==null) return false;
        else if(f.compare(element,n.getData()) == 0)
            return true;
        else if(f.compare(element, n.getData())>0){
            contains(element,n.getRight());
        }else if(f.compare(element, n.getData())<0){
            contains(element,n.getLeft());
        }return true;
    }
   
    public void posOrde(){
        posOrden(root);
    }
    private void posOrden(Nodo<E> n){
        if(n!=null){
            posOrden(n.getLeft());
            posOrden(n.getRight());
            System.out.println(n.getData());
        }
    }
    
    public void preOrden(){
        preOrden(root);
    }
    private void preOrden(Nodo<E> n){
        if(n!=null){
            System.out.println(n.getData());
            preOrden(n.getLeft());
            preOrden(n.getRight());           
        }
    }
    
    public void enOrden(){
        enOrden(root);
    }
    private void enOrden(Nodo<E> n){
        if(n!=null){
            enOrden(n.getLeft());
            System.out.println(n.getData());            
            enOrden(n.getRight());            
        }
    }
    
    public int nivel(E element){
        if (element==null|| isEmpty()|| this.contains(element)) return -1;
        return nivel(element, root);
    }
    
    private int nivel(E element, Nodo<E> n){
        int num=height()-1;
        if(n==null) return -1;      
        else if(f.compare(element,n.getData()) == 0){
            return num;
        }else if(f.compare(element, n.getData())>0){
            return nivel(element,n.getRight())-1;            
        }else if(f.compare(element, n.getData())<0){           
            return nivel(element,n.getLeft())-1;
        }return -1;
    }
        
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null) 
            return false;
        else if (!(this instanceof AVL) || !(obj instanceof AVL)){ 
            return false;
        }
        @SuppressWarnings("unchecked")
        final AVL<E> other = (AVL<E>) obj;
        return equals(this.root,other.root);    
    }
    private boolean equals(Nodo<E> n1, Nodo<E> n2){
        if(n1 == null && n2 == null)
            return true;
        else if((n1 == null && n2 != null) || (n1 != null && n2 == null))
            return false;
        else if(!n1.getData().equals(n2.getData())) {
            return false;
        }

        return equals(n1.getLeft(), n2.getLeft()) && equals(n1.getRight(), n2.getRight());
    } 

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    public Pane mostrarArbol(){
        Pane vbArbol = new Pane();
        Pane lineas = new Pane();
      
        mostrarArbol(vbArbol,root,0);
        for(int i = 0;i<height();i++){
            HBox hb = (HBox) vbArbol.getChildren().get(i);
            hb.setTranslateX(35*(Math.pow(2, ((height()-i)-1))-1));
            hb.setSpacing(20+60*(Math.pow(2, ((height()-i)-1))-1));
            if(i!=height()-1){
                for(int j = 0;j<hb.getChildren().size();j++){
                    
                    StackPane st = (StackPane) hb.getChildren().get(j);
                    if(((Circle)st.getChildren().get(0)).getOpacity()!=0){
                        Label lbl = (Label)st.getChildren().get(1);
                        Circle cir = (Circle)st.getChildren().get(0);
                        st.getChildren().clear();
                        HBox hbNext = (HBox) vbArbol.getChildren().get(i+1);
                        StackPane stNextIz = (StackPane)hbNext.getChildren().get(j*2);
                        StackPane stNextDer = (StackPane)hbNext.getChildren().get(j*2+1);
                                        
                        if(((Circle)stNextIz.getChildren().get(0)).getOpacity()!=0){
                            Path path = new Path(new PathElement[]{
                                new MoveTo(20+35*(Math.pow(2, ((height()-i)-1))-1)+j*(60+60*(Math.pow(2, ((height()-i-1)))-1)),20+i*70),
                                new LineTo(20+35*(Math.pow(2, ((height()-i)-2))-1)+j*2*(60+60*(Math.pow(2, ((height()-i-2)))-1)), 90+i*70)
                            });
                            lineas.getChildren().add(path);
                        }
                        if(((Circle)stNextDer.getChildren().get(0)).getOpacity()!=0){
                            Path path = new Path(new PathElement[]{
                                new MoveTo(20+35*(Math.pow(2, ((height()-i)-1))-1)+j*(60+60*(Math.pow(2, ((height()-i-1)))-1)),20+i*70),
                                new LineTo(20+35*(Math.pow(2, ((height()-i)-2))-1)+(j*2+1)*(60+60*(Math.pow(2, ((height()-i-2)))-1)), 90+i*70)
                            });
                            lineas.getChildren().add(path);
                        }
                        st.getChildren().addAll(cir,lbl);
                    
                    }
                                        
                }
            }
            
            
        }
        Pane sal = new Pane();
        sal.getChildren().addAll(lineas,vbArbol);
        return sal;
    }
    
    private void mostrarArbol(Pane vb,Nodo<E> n, int nivel){
        HBox hb;
        if(idexOutOfBoundsException(vb,nivel)){
            hb = new HBox();
            hb.setTranslateY(70*nivel);
            vb.getChildren().add(hb);
            
        }else hb = (HBox)vb.getChildren().get(nivel);
        Circle cir = new Circle(20);
        cir.setFill(Color.CHOCOLATE);
        StackPane st = new StackPane();
        st.getChildren().add(cir);
        hb.getChildren().add(st);
        if(n!=null){
            Label lbl = new Label(n.getData().toString());
            lbl.setTextFill(Color.WHITE);
            st.getChildren().add(lbl);
            mostrarArbol(vb,n.getLeft(),nivel+1);
            mostrarArbol(vb,n.getRight(),nivel+1);
        }else{
            
            cir.setOpacity(0);
            if(nivel<height()-1){
                mostrarArbol(vb,null,nivel+1);
                mostrarArbol(vb,null,nivel+1);
            }
        }
        
    }
    
    private boolean idexOutOfBoundsException(Pane vb, int n){
        try{
            vb.getChildren().get(n);
            return false;
        }catch(Exception ex){
            return true;
        }
    }
    
    /**Metodos para mostrar el arbol***/
    
    //EX remove
    public boolean delete(Object o) throws ClassCastException, NullPointerException{
    	Nodo<E> borrar=null,mirar=null,cambiar=null, nPadre = null;
    	Nodo<E> raizTmp = this.getRoot();
    	E c_aux;
    	boolean salir = false;
    	int altDer = 0;
    	int altIzq = 0;
    	int a = 0;
    	
    	if(this.isEmpty()){
    		return false;
    	}

        
        //el nodo a borrar es la raiz?
    	if(this.compararDato((E)o, raizTmp.getData())==0){
	    	salir = true;
	    	borrar = raizTmp;
	    }
    	
    	//si no es la raiz, lo buscamos
    	while(!salir && (raizTmp.getRight()!=null || raizTmp.getLeft()!=null)){

	    	if(this.compararDato((E)o, raizTmp.getData())>0){
	    		if(raizTmp.getRight()!=null){   		
	    			raizTmp = raizTmp.getRight();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararDato((E)o, raizTmp.getData())<0){
	    	
	    		if(raizTmp.getLeft()!=null){   
		    		raizTmp = raizTmp.getLeft();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararDato((E)o, raizTmp.getData())==0){
	    		salir = true;
	    		borrar = raizTmp;
	    	}
    	}
    

    	//existe el nodo a borrar?
    	if(salir){
    		mirar = borrar;

	    	//Hoja
	    	if(borrar.getLeft()==null && borrar.getRight()==null){
	    		mirar= padre(borrar);
	    		nPadre = padre(borrar);
	    		
	    		//es un arbol raiz con solo un nodo raiz?
	    		if(this.size()==1){
	    			this.root = null;
	    		}
	    		
	    		if(nPadre.getLeft()!=null && compararDato(nPadre.getLeft().getData(), borrar.getData())==0){
	    			nPadre.setLeft(null);
	    		}else if(nPadre.getRight()!=null && compararDato(nPadre.getRight().getData(), borrar.getData())==0){
	    			nPadre.setRight(null);
	    		}
	    		
	    		borrar.setData(null);
	    	}
	    	
	    	
	    	else if(borrar.getAltura()<=2){

	    		if(borrar.getLeft()!=null){
	    			borrar.setData(borrar.getLeft().getData());
	    			borrar.setLeft(null);
	    		}
	    		
	    		else if(borrar.getRight()!=null){
	    			borrar.setData(borrar.getRight().getData());
	    			borrar.setRight(null);
	    		}
	    	}
	    	
	    	//Nodo no hoja 
	    	else{

	    		//mayor de la izquierda
		    	if(borrar.getLeft()!=null){
		    		cambiar = borrar.getLeft();
		    		
		    		while(cambiar.getRight()!=null){
		    			cambiar = cambiar.getRight();
		    		}
		    	}
		    		
		    	//menor de la derecha
		    	else if(borrar.getRight()!=null){
		    		//cambiar = cambiar.getRight();//cambio
                                cambiar = borrar.getRight();
		    		while(cambiar.getLeft()!=null){
		    			cambiar = cambiar.getLeft();
		    		}
		    	}
	    	
		    	c_aux = cambiar.getData();
		    	Nodo<E> papa = padre(cambiar);
		    	
		    	
		    	if(cambiar.getLeft()!=null || cambiar.getRight()!=null){
			    	if(cambiar.getLeft()!=null){
			    		cambiar.setData(cambiar.getLeft().getData());
			    		cambiar.setLeft(null);
			    	}else if(cambiar.getRight()!=null){
			    		cambiar.setData(cambiar.getRight().getData());
			    		cambiar.setRight(null);
			    	}
		    	}
		    	
		    	else{		    	
			    	if(papa.getLeft()!=null && compararDato(papa.getLeft().getData(), cambiar.getData())==0){
			    		papa.setLeft(null);
			    	}else{
			    		papa.setRight(null);
			    	}
			    	cambiar.setData(borrar.getData());
			    	borrar.setData(c_aux);
		    	}		    
	    	}
	    	
	    	while(equilibrado(this.getRoot())<0){
    			if(mirar.getRight()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = mirar.getRight().getAltura();
	    		}
	    		
	    		if(mirar.getLeft()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = mirar.getLeft().getAltura();
	    		}
	    		
    			Nodo<E> cambiar2 = estructurar(mirar, altIzq, altDer);
    			Nodo<E> superior = padre(mirar);
    			
    			
    			if(compararDato(superior.getData(), mirar.getData())!=0){
    				if(superior.getLeft()!=null && compararDato(superior.getLeft().getData(), mirar.getData())==0){
	    				superior.setLeft(cambiar2);		
		    		}
		    		else if(superior.getRight()!=null && compararDato(superior.getRight().getData(), mirar.getData())==0){
	    				superior.setRight(cambiar2);
	    			}
    			}else{
    				this.root = cambiar2;
    			}
    			mirar = padre(mirar);
    		}
    		return true;	    	
    	}	
    	return false;
    }
    private int compararDato(E t1, E t2){
    	if(this.f==null){
    		return ((Comparable)t1).compareTo(t2);
    	}else{
    		return this.f.compare(t1,t2);
    	}
    }
    public Nodo<E> padre(Nodo<E> nodo){
	Nodo<E> raizTmp = this.getRoot();
	Stack <Nodo<E>> pila = new Stack <Nodo<E>>();
    	pila.push(raizTmp);	
    	while(raizTmp.getRight()!=null || raizTmp.getLeft()!=null){
	    	if(this.compararDato(nodo.getData(), raizTmp.getData())>0){
	    		if(raizTmp.getRight()!=null){   	
	    			raizTmp = raizTmp.getRight();
	    		}
	    	}
	    	else if(this.compararDato(nodo.getData(), raizTmp.getData())<0){	
	    		if(raizTmp.getLeft()!=null){   
		    		raizTmp = raizTmp.getLeft();
                }
                
	    	}
	    	if(this.compararDato(nodo.getData(), raizTmp.getData())==0){
	    		return pila.pop();
	    	}

	    	pila.push(raizTmp);	
    	}
    	return pila.pop();
    }
    public int size(){
    	return this.preOrden1().size();
    }
    private List<E> preOrden1(){
    	List<E> lista = new ArrayList<E>();
    	Nodo<E> nodo = this.getRoot();  	
    	Stack<Nodo<E>> pila = new Stack<Nodo<E>>();

     	while((nodo!=null && nodo.getData()!=null) || !pila.empty()){
     		if(nodo!=null){
     			lista.add(nodo.getData());
     			pila.push(nodo);
     			nodo = nodo.getLeft();
     		}else{
     			nodo = pila.pop();
     			nodo = nodo.getRight();
     		}
     	} 	
    	
    	return lista;
    }
    public int equilibrado(Nodo<E> n){
	int hIzq = 0;
	int hDer = 0;
		
	if(n==null){
    		return 0;
    	}
    	
    	hIzq = equilibrado(n.getLeft());
    	
    	if(hIzq < 0){
    		return hIzq;
    	}
    	
    	hDer = equilibrado(n.getRight());
    	
    	if(hDer <0){
    		return hDer;
    	}
    	
    	//si no es equilibrado
    	if(Math.abs(hIzq - hDer)>1){
    		return -1;
    	}
    	
    	//si el trozo de arbol es AVL devolvemos la altura
    	return Math.max(hIzq, hDer) + 1;
	}
    
    private Nodo<E> estructurar(Nodo<E> nodo, int altIzq, int altDer){
		if(extraeFactorE(nodo)==2){
			if( extraeFactorE(nodo.getRight())==1  || extraeFactorE(nodo.getRight()) == 0){
				//nodo = rotacionSimpleIzquierda1(nodo);
                                nodo=rotacionSimpleIzquierda(nodo);
			}
			
			else if(extraeFactorE(nodo.getRight())==-1){
				//nodo = rotacionCompuestaDerecha(nodo);
                                nodo=rotacionDobleDerecha(nodo);
			}
		}
		else if(extraeFactorE(nodo)==-2){
			if(extraeFactorE(nodo.getLeft())==-1 || extraeFactorE(nodo.getRight())==0){
				//nodo = rotacionSimpleDerecha1(nodo);
                                nodo=rotacionSimpleDerecha(nodo);
			}
			
			else if(extraeFactorE(nodo.getLeft())==1){
				//nodo = rotacionCompuestaIzquierda(nodo);
                                nodo=rotacionDobleIzquierda(nodo);
			}
		}

		return nodo;	
    }
    /*public Nodo<E> rotacionCompuestaIzquierda(Nodo<E> nodo){
    	Nodo<E> nodoTmp = nodo; //57

        nodoTmp = rotacionSimpleIzquierda1(nodoTmp.getLeft()); //param 42 | sale: 54
        
		nodo.setLeft(nodoTmp); //param 54

		nodoTmp = rotacionSimpleDerecha1(nodo); //param 54  | sale: 54
		
		return nodoTmp;
    }

	
    public Nodo<E> rotacionCompuestaDerecha(Nodo<E> nodo){
    	Nodo<E> nodoTmp = nodo;
    	
        nodoTmp = rotacionSimpleDerecha1(nodoTmp.getRight());
	
		nodo.setRight(nodoTmp);

		nodoTmp= rotacionSimpleIzquierda1(nodo);

		return nodoTmp;
    }
    public Nodo<E> rotacionSimpleIzquierda1(Nodo<E> nodo){
		Nodo<E> nodoTmp = nodo;
		
    	nodo = nodoTmp.getRight(); //clone??
		nodoTmp.setRight(nodo.getLeft());

		nodo.setLeft(nodoTmp);

		return nodo;
    }

	
    public Nodo<E> rotacionSimpleDerecha1(Nodo<E> nodo){
    	Nodo<E> nodoTmp = nodo;
    	nodo = nodoTmp.getLeft();

		nodoTmp.setLeft(nodo.getRight());
		nodo.setRight(nodoTmp);

		return nodo;
    }
  */
    public int extraeFactorE(Nodo<E> nodo){
    	if(nodo!=null){
    		return nodo.getFactorE();
    	}else{
    		return 0;
    	}
    }
}
