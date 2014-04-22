/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atos.ressources.service;

import com.atos.ressources.Gcm;
import com.atos.ressources.Rr;
import com.atos.ressources.service.utils.Utils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author a573405
 */
@Stateless
@Path("com.atos.ressources.rr")

public class RrFacadeREST extends AbstractFacade<Rr> {
    @PersistenceContext(unitName = "RRWebServicePU")
    private EntityManager em;

    public RrFacadeREST() {
        super(Rr.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Rr entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Rr entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Rr find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Rr> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Rr> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    /**
     * 
     * @param id
     * @param codeGcm
     * @param motscles
     * @param ville
     * @param from
     * @return 
     */
    @GET
    @Path("/listrr")
    @Produces(MediaType.APPLICATION_JSON)
    //@Interceptors(RrInterceptor.class)    
    public List<Rr> findRRByElements(@QueryParam("id") Integer id, @QueryParam("gcm") String codeGcm,@QueryParam("motscles") String motscles,
            @QueryParam("ville") String ville, @QueryParam("from") Date from){
        //Caster le String en date
        //Parsing null values
        if((codeGcm.trim().length() <1)){
            codeGcm = null;
        }
        
        if((ville.trim().length() <1)){
            ville = null;
        }
        
        if((motscles.trim().length() <1)){
            motscles = null;
        }
        List<Rr> results = new ArrayList<Rr>();
        
        try{
            if (id!=null){
                results = new ArrayList<Rr>();
                results.add(find(id));      
                //return (List<Rr>) find(id);
            }
            else{
                if(codeGcm!=null){
                    if(ville!=null){
                        if(from!=null){
                            if(motscles!=null){
                                //Aucun paramètre nul
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.ville = :ville AND r.gcmRr = :gcmRr AND r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class)
                                        .setParameter("ville", ville)
                                        .setParameter("from", from)
                                        .setParameter("gcmRr", codeGcm)
                                        .getResultList();
                            }
                            //motscles null
                            results = getEntityManager().createNamedQuery("Rr.findwithoutkeywords")
                                    .setParameter("gcmRr", codeGcm)
                                    .setParameter("ville", ville)
                                    .setParameter("from",from)
                                    .getResultList();
                        }
                        // gcm, ville non null et from null
                        else {
                            if(motscles!=null){
                                // gcm, ville, mots cles non null , from null
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.ville = :ville AND r.gcmRr = :gcmRr AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class)
                                        .setParameter("ville", ville)
                                        .setParameter("gcmRr", codeGcm)
                                        .getResultList();                                
                                
                                
                                
                            }
                            else{
                                //gcm , ville non null, from null et mots cles null
                                results = getEntityManager().createNamedQuery("Rr.findwithgcmandville")
                                        .setParameter("gcmRr", codeGcm)
                                        .setParameter("ville", ville)
                                        .getResultList();
                            }
                        }
                    }
                    else{
                        // gcm non nul et vill nulls
                        if(from!=null){
                            // gcm non null, from non null et ville nul
                            if(motscles!=null){
                                // gcm non null, from non null, mots cles non null et ville nul
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class)
                                        .setParameter("from", from)
                                        .setParameter("gcmRr", codeGcm)
                                        .getResultList();
                                
                            }
                            else{
                            // gcm non null, from non null , ville nul, mots cles null
                                results = getEntityManager().createNamedQuery("Rr.findwithfromandgcm")
                                        .setParameter("gcmRr", codeGcm)
                                        .setParameter("from", from)
                                        .getResultList();
                            }
                        }                       
                        else{
                            //gcm non null, ville null, from null
                            if(motscles!=null){
                                // seuls gcm et mots cles sont non null
                                
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.gcmRr = :gcmRr AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class)
                                        .setParameter("gcmRr", codeGcm)
                                        .getResultList();                                                                
                            }
                            else{
                                //seul gcm est non null
                                results = getEntityManager().createNamedQuery("Rr.findByGcmRr")
                                        .setParameter("gcmRr", codeGcm)
                                        .getResultList();
                            }
                        }
                    }
                }
                // gcm null
                else{
                    if(ville!=null){
                        //gcm null, ville nonnul
                        if(from!=null){
                            if(motscles!=null){
                                // gcm null et ville, from, mots cles non null
                                
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.ville = :ville AND r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class)
                                        .setParameter("ville", ville)
                                        .setParameter("from", from)
                                        .getResultList();
                            }
                            else{
                                // gcm null, motscles null et from, ville non null
                                results = getEntityManager().createNamedQuery("Rr.findwithfromandville")
                                    .setParameter("ville", ville)
                                    .setParameter("from",from)
                                    .getResultList();
                            }
                        }
                        else{
                            // gcm null,  from null,ville non nul,
                            if(motscles!=null){
                                // ville et mos cles non null, le reste null
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.ville = :ville AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class).setParameter("ville", ville).getResultList();
                               
                            }
                            else{
                                // only ville non null
                                results = getEntityManager().createNamedQuery("Rr.findByVille")
                                    .setParameter("ville", ville)
                                    .getResultList();
                            }
                        }
                    }
                    else{
                        // gcm et ville null
                        if(from!=null){
                            //gcm, ville null; from non null
                            if(motscles!=null){
                                // from et mots cles non null the others null
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request,Rr.class)
                                        .setParameter("from", from)
                                        .getResultList();
                                
                            }
                            else{
                                //only from non null
                                results = getEntityManager().createNamedQuery("Rr.findByDateDebut")
                                    .setParameter("dateDebut", from)
                                    .getResultList();
                            }
                        }
                        else{
                            // gcm, ville et from null
                            // only mots cles non null                          
                                List <String> keywords = Utils.parse(motscles);
                                String request = "SELECT DISTINCT r FROM Rr r WHERE (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    request = request + "r.competenceRr LIKE '%"+keyword +"%' OR r.role LIKE '%"+keyword+"%'";
                                    cpt++;
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                                request = request + ")";
                                results = getEntityManager().createQuery(request).getResultList();
                        }
                    }
                }
            }
        }
        catch(Exception e){
            //Do something here
        }
        return results;        
    }
    
    @GET
    @Path("cities")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCities(){
        
        List results = new ArrayList();
        results = getEntityManager().createNamedQuery("Rr.findAllCities").getResultList();
        
        List<City> cities = new ArrayList<City>();
        for(Object city  : results){
            if((city.toString().trim()).length()>0)
                cities.add(new City(city.toString()));
        }
        //return results.toString();
        Gson json = new Gson();
        return json.toJson(cities);
    }
    
    @GET
    @Path("allgcm")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllGcm(){
        
        List results = new ArrayList();
        results = getEntityManager().createNamedQuery("Rr.findAllGcm",Gcm.class).getResultList();
        
        List<Gcm> gcms = new ArrayList<Gcm>();
        for(Object gcm  : results){
            if((gcm.toString().trim()).length()>0){
                Gcm aux = new Gcm();
                aux.setCode(gcm.toString());
                gcms.add(aux);
            }
        }
        //return results.toString();
        Gson json = new Gson();
        return json.toJson(gcms);
    }
    
    
    @GET
    @Path("listgcm")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gcm> getGms(){
        List results = new ArrayList();
        return results = getEntityManager().createNamedQuery("Gcm.findAll",Gcm.class).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
}

class City{
    private String ville;
    
    public City(String ville){
        this.ville = ville;
    }
    public String getVille(){
        return this.ville;
    }
}