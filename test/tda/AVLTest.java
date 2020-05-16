/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ktiusk
 */
public class AVLTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRoot method, of class AVL.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        AVL instance = null;
        Nodo expResult = null;
        Nodo result = instance.getRoot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoot method, of class AVL.
     */
    @Test
    public void testSetRoot() {
        System.out.println("setRoot");
        AVL instance = null;
        instance.setRoot(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class AVL.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        AVL instance = null;
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of height method, of class AVL.
     */
    @Test
    public void testHeight() {
        System.out.println("height");
        AVL instance = new AVL<>(Integer::compareTo);
        instance.Insert(8);
        instance.Insert(2);
        int expResult = 2;
        int result = instance.height();
        assertEquals(expResult, result);
        if (result != expResult)
            fail("The test case is a prototype.");
    }

    /**
     * Test of totalNodos method, of class AVL.
     */
    @Test
    public void testTotalNodos() {
        System.out.println("totalNodos");
        AVL instance = new AVL<>(Integer::compareTo);
        instance.Insert(8);
        instance.Insert(2);
        int expResult = 2;
        int result = instance.totalNodos();
        assertEquals(expResult, result);
        if (result != expResult)
            fail("The test case is a prototype.");
    }

    /**
     * Test of contarHojas method, of class AVL.
     */
    @Test
    public void testContarHojas() {
        System.out.println("contarHojas");
        AVL instance = new AVL<>(Integer::compareTo);
        instance.Insert(8);
        instance.Insert(2);
        int expResult = 1;
        int result = instance.contarHojas();
        assertEquals(expResult, result);
        if (result != expResult)
            fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class AVL.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object element = null;
        AVL instance = null;
        boolean expResult = false;
        boolean result = instance.add(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Insert method, of class AVL.
     */
    @Test
    public void testInsert() {
        System.out.println("Insert");
        Nodo<Integer> element = null;
        AVL instance = new AVL<>(Integer::compareTo);
        instance.Insert(8);
        instance.Insert(2);
        boolean expResult = false;
        boolean result = instance.Insert(element);
        assertFalse(result);
        // TODO review the generated test code and remove the default call to fail.
        if (result != expResult)
            fail("The test case is a prototype.");
    }

    /**
     * Test of deleteNode method, of class AVL.
     */
    @Test
    public void testDeleteNode() {
        System.out.println("deleteNode");
        Object element = null;
        AVL instance = null;
        boolean expResult = false;
        boolean result = instance.delete(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of esDegenerado method, of class AVL.
     */
    @Test
    public void testEsDegenerado() {
        System.out.println("esDegenerado");
        AVL instance = null;
        boolean expResult = false;
        boolean result = instance.esDegenerado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularCarga method, of class AVL.
     */
    @Test
    public void testCalcularCarga() {
        System.out.println("calcularCarga");
        AVL instance = new AVL<>(Integer::compareTo);
        instance.Insert(8);
        instance.Insert(2);
        int expResult = -1;
        int result = instance.calcularCarga();
        assertEquals(expResult, result);
        if (result != expResult)
            fail("The test case is a prototype.");
    }

    /**
     * Test of rotacionSimpleIzquierda method, of class AVL.
     */
    @Test
    public void testRotacionSimpleIzquierda() {
        System.out.println("rotacionSimpleIzquierda");
        AVL instance = null;
        Nodo expResult = null;
        Nodo result = instance.rotacionSimpleIzquierda(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotacionSimpleDerecha method, of class AVL.
     */
    @Test
    public void testRotacionSimpleDerecha() {
        System.out.println("rotacionSimpleDerecha");
        AVL instance = null;
        Nodo expResult = null;
        Nodo result = instance.rotacionSimpleDerecha(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotacionDobleIzquierda method, of class AVL.
     */
    @Test
    public void testRotacionDobleIzquierda() {
        System.out.println("rotacionDobleIzquierda");
        AVL instance = null;
        Nodo expResult = null;
        Nodo result = instance.rotacionDobleIzquierda(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotacionDobleDerecha method, of class AVL.
     */
    @Test
    public void testRotacionDobleDerecha() {
        System.out.println("rotacionDobleDerecha");
        AVL instance = null;
        Nodo expResult = null;
        Nodo result = instance.rotacionDobleDerecha(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of max method, of class AVL.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        AVL instance = null;
        Object expResult = null;
        Object result = instance.max();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of min method, of class AVL.
     */
    @Test
    public void testMin() {
        System.out.println("min");
        AVL instance = null;
        Object expResult = null;
        Object result = instance.min();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class AVL.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object element = null;
        AVL instance = null;
        boolean expResult = false;
        boolean result = instance.contains(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of posOrde method, of class AVL.
     */
    @Test
    public void testPosOrde() {
        System.out.println("posOrde");
        AVL instance = null;
        instance.posOrde();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preOrden method, of class AVL.
     */
    @Test
    public void testPreOrden() {
        System.out.println("preOrden");
        AVL instance = null;
        instance.preOrden();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enOrden method, of class AVL.
     */
    @Test
    public void testEnOrden() {
        System.out.println("enOrden");
        AVL instance = null;
        instance.enOrden();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nivel method, of class AVL.
     */
    @Test
    public void testNivel() {
        System.out.println("nivel");
        Object element = null;
        AVL instance = null;
        int expResult = 0;
        int result = instance.nivel(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class AVL.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        AVL instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class AVL.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        AVL instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostrarArbol method, of class AVL.
     */
    @Test
    public void testMostrarArbol() {
        System.out.println("mostrarArbol");
        AVL instance = null;
        Pane expResult = null;
        Pane result = instance.mostrarArbol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
