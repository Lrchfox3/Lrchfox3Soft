/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;


import com.gmail.lrchfox3.model.dto.Catalogo;
import com.gmail.lrchfox3.basedatos.Campo;
import com.gmail.lrchfox3.basedatos.SqlTipos;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chinchillal
 */
public class Periodo extends Base implements Catalogo {

    protected Campo codigo_periodo = null; //1
    protected Campo periodo = null; //2

    public Periodo() {
        codigo_periodo = new Campo("codigo_periodo", "Código", SqlTipos.INTEGER, true, false);
        periodo = new Campo("periodo", "Período", SqlTipos.VARCHAR);       
        
        setTabla("PERIODO");
        setTitulo("Período");
    }

    public Campo Codigo() {
        return codigo_periodo;
    }

    public Campo Periodo() {
        return periodo;
    }

    public int getCodigo() {
        return codigo_periodo.getIntValue();
    }

    public void setCodigo(int codigo) {
        this.codigo_periodo.setValue(codigo);
    }

    public String getPeriodo() {
        return periodo.getStringValue();
    }

    public void setPeriodo(String nombre) {
        this.periodo.setValue(nombre);
    }

    public String getNombreTabla() {
        return this.getTabla();
    }

    public List<Campo> getCampos() throws Exception {
        List<Campo> lst = new ArrayList<Campo>();
        /*Field[] vars =  this.getClass().getDeclaredFields();
        for (Field var : vars) {
            System.out.println(var.getName());
            if (var.getType().getName().equalsIgnoreCase("ControlClases.utilitarios.Campo")) {
                Campo campo = new Campo();
campo = (Campo)var.getType().cast(campo);

                System.out.println( campo.getEtiqueta() );
                //lst.add( (Campo) var.get(new Campo()));
            }
        }*/

        lst.add(codigo_periodo);
        lst.add(periodo);
        return lst;
    }

    public String toString(){
        return getPeriodo();
    }
}
