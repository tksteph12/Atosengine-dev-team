/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atos.ressources.service;

import com.atos.ressources.Rr;
import com.atos.ressources.service.utils.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    @Path("/testings")
    @Produces(MediaType.APPLICATION_JSON)
    //@Interceptors(RrInterceptor.class)    
    public List<Rr> findRRByElements(@QueryParam("id") Integer id, @QueryParam("gcm") String codeGcm,@QueryParam("motscles") String motscles,
            @QueryParam("ville") String ville, @QueryParam("from") Date from){
        
        List<Rr> results = null;
        
        try{
            if (id!=null){
                results = new ArrayList<Rr>();
                results.add(find(id));      
                //return (List<Rr>) find(id);
            } 
            else{
                results = new ArrayList<Rr>();
                if(codeGcm !=null){
                    if(ville !=null){
                        if (from != null){
                            if (motscles !=null){
                                List <String> keywords = Utils.parse(motscles);
                                //All criterias non null
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.ville = :ville AND r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    
                                    request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE :"+keyword;
                                    cpt++;
                                    
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                              
                                request = request + ")";
                                TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                query.setParameter("gcmRr", codeGcm)
                                        .setParameter("ville", ville)
                                        .setParameter("from", from);
                                for(String keyword : keywords){
                                    query.setParameter(keyword, "%"+keyword+"%");
                                }
                              
                                results = query.getResultList();
                            }
                            
                            //motscle = null
                             results = getEntityManager().createNamedQuery("Rr.findwithoutkeywords",Rr.class).getResultList();
                            
                        }
                        
                        else{ 
                            if(motscles!=null){
                                List <String> keywords = Utils.parse(motscles);
                                    //All criterias non null: from = null
                                    String request = "SELECT DISTINCT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.ville = :ville AND (";
                                    int cpt = 0;
                                    for(String keyword : keywords){

                                        request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE :"+keyword;
                                        cpt++;

                                        if(cpt < keywords.size()){
                                            request = request + " OR ";
                                        }
                                    }

                                    request = request + ")";
                                    TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                    query.setParameter("gcmRr", codeGcm)
                                            .setParameter("ville", ville);
                                    for(String keyword : keywords){
                                        query.setParameter(keyword, "%"+keyword+"%");
                                    }

                                    results = query.getResultList();
                            }
                        }
                        
                    }
                    
                    else{
                        if (from!=null){
                            if(motscles!=null){
                                //filtre par from, keyword et code gcm
                                
                                List <String> keywords = Utils.parse(motscles);
                                //All criterias non null
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.gcmRr = :gcmRr AND r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    
                                    request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE :"+keyword;
                                    cpt++;
                                    
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                              
                                request = request + ")";
                                TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                query.setParameter("gcmRr", codeGcm)
                                        .setParameter("from", from);
                                for(String keyword : keywords){
                                    query.setParameter(keyword, "%"+keyword+"%");
                                }
                              
                                results = query.getResultList();
                                
                                
                            }
                            else{
                                //keyworks = null : filtre par from et code gcm
                                results = getEntityManager().createNamedQuery("Rr.findwithfromandgcm",Rr.class).getResultList();
                            }
                        }
                    }
                    
                }
                else{
                    //code gcm = null
                    if(ville!=null){
                        if (from!=null){
                            if(motscles !=null){
                                // filtre par ville, from et keywords : gcm = null
                                
                                List <String> keywords = Utils.parse(motscles);
                                //All criterias non null
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.ville = :ville AND r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    
                                    request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE :"+keyword;
                                    cpt++;
                                    
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                              
                                request = request + ")";
                                TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                query.setParameter("ville", ville)
                                        .setParameter("from", from);
                                for(String keyword : keywords){
                                    query.setParameter(keyword, "%"+keyword+"%");
                                }
                              
                                results = query.getResultList();
                            }
                            else{
                                // filtre par ville, from
                                results = getEntityManager().createNamedQuery("Rr.findwithfromandville",Rr.class).getResultList();
                            }
                        }
                        else{
                            if (motscles!=null){
                                // filtre par keyword et ville
                                
                                List <String> keywords = Utils.parse(motscles);
                                //All criterias non null
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.ville = :ville AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    
                                    request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE :"+keyword;
                                    cpt++;
                                    
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                              
                                request = request + ")";
                                TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                query.setParameter("ville", ville);
                                for(String keyword : keywords){
                                    query.setParameter(keyword, "%"+keyword+"%");
                                }
                              
                                results = query.getResultList();
                               
                            }
                            else{
                                // filtre par ville
                                results = getEntityManager().createNamedQuery("Rr.findByVille",Rr.class).getResultList();
                            }
                        }
                    }
                    else {
                        
                        // code gcm et ville null: 
                        
                        if(motscles !=null){
                            if(from!=null){
                                List <String> keywords = Utils.parse(motscles);
                                //All criterias non null
                                String request = "SELECT DISTINCT r FROM Rr r WHERE r.dateDebut >= :from AND (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    
                                    request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE :"+keyword;
                                    cpt++;
                                    
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                              
                                request = request + ")";
                                TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                query.setParameter("from", from);
                                for(String keyword : keywords){
                                    query.setParameter(keyword, "%"+keyword+"%");
                                }
                              
                                results = query.getResultList();                               
                            }
                            else{
                                
                                List <String> keywords = Utils.parse(motscles);
                                //All criterias non null
                                String request = "SELECT DISTINCT r FROM Rr r WHERE (";
                                int cpt = 0;
                                for(String keyword : keywords){
                                    
                                    request = request + "r.competenceRr LIKE : "+keyword +" OR r.role LIKE : "+keyword;
                                    cpt++;
                                    
                                    if(cpt < keywords.size()){
                                        request = request + " OR ";
                                    }
                                }
                              
                                request = request + ")";
                                TypedQuery query = getEntityManager().createQuery(request, Rr.class);
                                for(String keyword : keywords){
                                    query.setParameter(keyword, "%"+keyword+"%");
                                }
                              
                                results = query.getResultList();
                                
                            }
                        }
                        else{
                            if (from!=null){
                                results = getEntityManager().createNamedQuery("Rr.findByDateDebut",Rr.class).getResultList();
                            }
                            
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
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCities(){
        List results = new ArrayList();
        results = getEntityManager().createNamedQuery("Rr.findAllCities",Rr.class).getResultList();
        
        return results.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
