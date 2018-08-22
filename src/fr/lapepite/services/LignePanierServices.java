package fr.lapepite.services;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import fr.lapepite.javabean.LignePanier;
import fr.lapepite.javabean.Panier;
import fr.lapepite.javabean.Utilisateur;

public class LignePanierServices {

	public Utilisateur addOrDropOneToQuantity(Utilisateur utilisateur, boolean b, int idBijoux) {

		Panier panier = utilisateur.getPanier();

		ArrayList<LignePanier> listLignePanier =  (ArrayList<LignePanier>) panier.getListProduit();

		for (LignePanier lignePanier : listLignePanier) {

			if (lignePanier.getBijoux().getId_bijoux() == idBijoux) {

				if (b) {

					lignePanier.addOne();

				} else {

					if (lignePanier.getQuantite_lignepanier()==1) {

						listLignePanier.remove(lignePanier);

					} else {

						lignePanier.dropOne();

					}

				}

			}

		}

		return utilisateur;

	}

}
