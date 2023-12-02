package services;

import java.util.List;


import dao.IDaoLocale;
import dao.IDaoRemote;

import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "kenza")
public class VilleService implements IDaoRemote<Ville>, IDaoLocale<Ville>  {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@PermitAll
	public Ville create(Ville o) {
		em.persist(o);
		return o;
	}


	@Override
	@PermitAll
	public boolean delete(Ville o) {
	    if (o != null) {
	        // Check if the entity is managed before trying to remove it
	        if (em.contains(o)) {
	            em.remove(o);
	        } else {
	            // If the entity is detached, merge it first and then remove
	            Ville managedEntity = em.merge(o);
	            em.remove(managedEntity);
	        }
	        return true;
	    }
	    return false;
	}

	@Override
	@PermitAll
	public Ville update(Ville o) {
	    Ville existingVille = em.find(Ville.class, o.getId());

	    if (existingVille != null) {
	        // Mettre à jour les propriétés de l'entité existante avec les nouvelles valeurs
	        existingVille.setNom(o.getNom());
	      
	        // Ajouter d'autres propriétés à mettre à jour...

	        // Mettre à jour l'entité dans la base de données
	        em.merge(existingVille);
	    }

	    return o;
	}

	@Override
	@PermitAll
	public Ville findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	@Override
	@PermitAll
	public List<Ville> findAll() {
		Query query = em.createQuery("select v from Ville v");
		List<Ville> villes = query.getResultList();
	    
	    // Ajouter un log pour vérifier les données récupérées
	    System.out.println("Nombre de villes récupérées : " + villes.size());
	    
	    return villes;
		//return query.getResultList();
	}


	@Override
	public List<Ville> findByVille(int villeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
