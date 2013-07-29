/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAC;


import com.gmail.lrchfox3.basedatos.BD;
import com.gmail.lrchfox3.utilitarios.Excel;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.DTO.Carrera;
import com.gmail.lrchfox3.model.dto.Catalogo;

/**
 *
 * @author Administrador
 */
public class CarreraDAC extends BD {

    private Carrera dtoBase = new Carrera();

    public List<Carrera> getListaCarreras() throws SQLException, Exception {
        List<Carrera> lst = new ArrayList<Carrera>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla();
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);        
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    public List<Catalogo> getListaCampos() throws SQLException, Exception {

        List<Catalogo> lst = new ArrayList<Catalogo>();

        for (Carrera p : getListaCarreras()) {
            lst.add(p);

        }
        return lst;
    }

    public Carrera getCarrera(int codigo) throws SQLException, Exception {
        Carrera dto = new Carrera();
        String sql = "SELECT " + dtoBase.getNombreCampos()
                + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = " + codigo;
        Statement st = crearSentencia();

        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            dto = getRegistro(rs);
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return dto;
    }

    public Catalogo getCatalogoCarrera(int codigo) throws SQLException, Exception {
        Catalogo dto = (Catalogo) getCarrera(codigo);
        return dto;
    }

    public List<Carrera> buscarPorCriterio(String criterio) throws SQLException, Exception {
        List<Carrera> lst = new ArrayList<Carrera>();
        String sql = "SELECT " + dtoBase.getNombreCampos() + " FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(2) + " like '%" + criterio + "%' OR " + dtoBase.getNombreCampo(3) + " like '%" + criterio + "%' ";
        Statement st = crearSentencia();
        ResultSet rs = st.executeQuery(sql);
        //rs.first();
        while (rs.next()) {
            lst.add(getRegistro(rs));
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lst;
    }

    public List<Catalogo> buscarEnCatalogo(String criterio) throws SQLException, Exception {
        List<Catalogo> lst = new ArrayList<Catalogo>();

        for (Carrera p : buscarPorCriterio(criterio)) {
            lst.add(p);

        }
        return lst;
    }

    private Carrera getRegistro(ResultSet prs) throws SQLException, Exception {
        Carrera dto = new Carrera();
        dto.setCodigo(prs.getInt(dtoBase.getNombreCampo(1)));
        dto.setNombre(prs.getString(dtoBase.getNombreCampo(2)));
        dto.setCodigo_referencia(prs.getString(dtoBase.getNombreCampo(3)));
        dto.setCodigo_universidad(prs.getInt(dtoBase.getNombreCampo(4)));
        return dto;
    }

    public boolean editar(int accion, Carrera dto) throws SQLException, Exception {
        String sql = "";
        PreparedStatement pstr = null;
        if (accion == BD.ACCION_AGREGAR) {
            sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampos(new int[]{2, 3, 4}) + ") VALUES(?, ?, ?)";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombre());
            pstr.setString(2, dto.getCodigo_referencia());
            pstr.setInt(3, dto.getCodigo_universidad());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_EDITAR) {
            sql = "UPDATE " + dtoBase.getTabla() + " SET " + dtoBase.getNombreCampo(2) + " = ?, " + dtoBase.getNombreCampo(3) + " = ?, " + dtoBase.getNombreCampo(4) + " = ?, WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setString(1, dto.getNombre());
            pstr.setString(2, dto.getCodigo_referencia());
            pstr.setInt(3, dto.getCodigo_universidad());
            pstr.setInt(4, dto.getCodigo());
            pstr.executeUpdate();
            commit();
        } else if (accion == BD.ACCION_ELIMINAR) {
            sql = "DELETE FROM " + dtoBase.getTabla() + " WHERE " + dtoBase.getNombreCampo(1) + " = ?";
            pstr = prepararSentencia(sql);
            pstr.setInt(1, dto.getCodigo());
            pstr.executeUpdate();
            commit();
        }

        if (pstr != null) {
            pstr.close();
        }
        return true;
    }

    public boolean editar(int accion, Catalogo cat) throws SQLException, Exception {
        Carrera dto = (Carrera) cat;
        return editar(accion, dto);
    }

    public boolean importarCarreraDesdeExcel(String archivo) throws Exception {
        String sql = "INSERT INTO " + dtoBase.getTabla() + "(" + dtoBase.getNombreCampos(new int[]{2, 3, 4}) + ") VALUES(?,?, ?)";
        int tipoDatos[] = {Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        Excel excel = new Excel();
        excel.importarExcel(archivo, sql, tipoDatos);
        return true;
    }
}
