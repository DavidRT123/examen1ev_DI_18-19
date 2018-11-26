package examen1ev1819;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.TitledBorder;

public class Frame extends JFrame {

    int columnas, filas;
    ButtonGroup buttongroup;
    TitledBorder titulo;
    ImageIcon imagen;
    TextField textfield;
    JCheckBox checkbox;
    JRadioButton radiobutton;
    JList list;
    DefaultListModel listModel;
    JButton button;
    JPanel panelPrincipal, primero, segundo, tercero, cuarto, quinto, sexto;
    //Matriz de string con los datos iniciales, 
    //el programa debe funcionar si se añaden, eliminan o modifican "líneas":
    String[][] datosIniciales = {
        {"Primero", "imagen", "1.png", "2.png", "3.png", "4.png"},
        {"Segundo", "textfield", "TextBox 1", "TextBox 2", "TextBox 3", "TextBox 4", "TextBox 5"},
        {"tercero", "checkbox", "CheckBox 1", "CheckBox 2", "CheckBox 3", "CheckBox 4", "CheckBox 5", "CheckBox 6", "CheckBox 7", "CheckBox 8", "CheckBox 9", "CheckBox 10", "CheckBox 11", "CheckBox 12", "CheckBox 13", "CheckBox 14", "CheckBox 15"},
        {"Cuarto", "radiobutton", "RadioButton 1", "RadioButton 2", "RadioButton 3", "RadioButton 4", "RadioButton 5", "RadioButton 6", "RadioButton 7",},
        {"Quinto", "list", "Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5", "Elemento 6", "Elemento 7", "Elemento 8", "Elemento 9", "Elemento 10", "Elemento 11", "Elemento 12"},
        {"Sexto", "button", "Botón 1", "Botón 2", "Botón 3"},};
    String disposicion = "vertical"; //valores posibles: vertical, horizontal,cuadricula2x y cuadricula3x

    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Generador de Ventanas");
        panelPrincipal = new JPanel();
        add(panelPrincipal);

        switch (disposicion) {
            case "horizontal":
                filas = 1;
                columnas = 6;
                break;
            case "cuadricula2x":
                filas = 3;
                columnas = 2;
                break;
            case "cuadricula3x":
                filas = 2;
                columnas = 3;
                break;
            case "vertical":
            default:
                columnas = 1;
                filas = 6;
        }

        panelPrincipal = new JPanel(new GridLayout(filas, columnas));

        //Primero
        primero = new JPanel(new FlowLayout());
        valoresPanel(0, primero);

        //Segundo
        segundo = new JPanel(new GridLayout(datosIniciales[1].length-2, 2, 0, 5));
        valoresPanel(1, segundo);

        //Tercero
        tercero = new JPanel(new GridLayout(calcularFilas(datosIniciales[2].length-2, 5), 5, 0, 20));
        valoresPanel(2, tercero);

        //Cuarto
        cuarto = new JPanel(new GridLayout(calcularFilas(datosIniciales[3].length-2, 4), 4));
        buttongroup = new ButtonGroup();
        valoresPanel(3, cuarto);

        //quinto
        quinto = new JPanel(new GridLayout());
        listModel = new DefaultListModel();
        valoresPanel(4, quinto);
        list = new JList(listModel);
        list.setVisibleRowCount( 8 );
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        //añado la lista al panel con una barra de desplazamiento
        quinto.add( new JScrollPane(list) );
        
        //Sexto
        sexto = new JPanel(new FlowLayout());
        valoresPanel(5, sexto);
        
        //Añadir paneles
        panelPrincipal.add(primero);
        panelPrincipal.add(segundo);
        panelPrincipal.add(tercero);
        panelPrincipal.add(cuarto);
        panelPrincipal.add(quinto);
        panelPrincipal.add(sexto);
        
        //Añadir panel
        add(panelPrincipal);
        
    }

    public void valoresPanel(int indice, JPanel panel) {
        //Servirá para incluir en el panel correspondiente el elemento correspondiente
        Component componente = null;
        int i, longitud = datosIniciales[indice].length;
        titulo = new TitledBorder(datosIniciales[indice][0]);
        panel.setBorder(titulo);
        
        for (i = 2; i <  longitud; i++) {
            switch (datosIniciales[indice][1]) {
                case "imagen":
                    componente = rellenarImagen(indice, i, panel);
                    break;
                case "textfield":
                    componente = rellenarText(indice, i, panel);
                    break;
                case "checkbox":
                    componente = rellenarCheck(indice, i, panel);
                    break;
                case "radiobutton":
                    componente = rellenarRadio(indice, i, panel);
                    break;
                case "list":
                    rellenarList(indice, i, panel);
                    break;
                case "button":
                    componente = rellenarButton(indice, i, panel);
                    break;
            }
            if(componente != null){
                panel.add(componente);
            }
        }
    }
    
    public Component rellenarImagen(int indice, int fila, JPanel panel){
        imagen = new ImageIcon(getClass().getResource("/imagenes/" + datosIniciales[indice][fila]));
        JLabel etiqueta = new JLabel();
        etiqueta.setIcon(imagen);
        etiqueta.setToolTipText(datosIniciales[indice][fila]);
        return etiqueta;
    }
    
    public Component rellenarText(int indice, int fila, JPanel panel){
        JLabel etiqueta = new JLabel(datosIniciales[indice][fila]);
        panel.add(etiqueta);
        return textfield = new TextField();
    }
    
    public Component rellenarCheck(int indice, int fila, JPanel panel){
        return checkbox = new JCheckBox(datosIniciales[indice][fila]);
    }
    
    public Component rellenarRadio(int indice, int fila, JPanel panel){
        radiobutton = new JRadioButton(datosIniciales[indice][fila]);
        buttongroup.add(radiobutton);
        return radiobutton;
    }
    
    public void rellenarList(int indice, int fila, JPanel panel){
        listModel.addElement(datosIniciales[indice][fila]);
    }
    
    public Component rellenarButton(int indice, int fila, JPanel panel){
        button = new JButton(datosIniciales[indice][fila]);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Click en el Botón " + (fila-1));
            }
        });
        return button;
    }
    
    /**
     * Método auxiliar para calcular el número de filas en función de el número de columnas 
     * Se usa para mantener fijas siempre las columnas.
     * @param registros
     * @param columnasFijas
     * @return filas
     */
    public int calcularFilas(int registros, int columnasFijas){
        int filas;
        if((registros % columnasFijas) != 0){
            filas = (registros / columnasFijas) + 1;
        }else{
            filas = (registros / columnasFijas);
        }
        return filas;
    }
}

