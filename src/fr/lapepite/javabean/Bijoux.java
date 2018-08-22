package fr.lapepite.javabean;

public class Bijoux {
    
    private int id_bijoux;
    
    private String nom_bijoux;
    
    private String ref_bijoux;
    
    private int prix_bijoux;
    
    private int stock_bijoux;
    
    private String image_bijoux;
    
    private String description_bijoux;
    
    private Designer designer;
    
    private Categorie categorie;
    
    
    public int getId_bijoux() {
        return id_bijoux;
    }
    
    public Bijoux setId_bijoux(int id) {
        this.id_bijoux = id;
        return this;
    }
    
    public String getNom_bijoux() {
        return nom_bijoux;
    }
    
    public Bijoux setNom_bijoux(String nom) {
        this.nom_bijoux = nom;
        return this;
    }
    
    public String getRef_bijoux() {
        return ref_bijoux;
    }
    
    public Bijoux setRef_bijoux(String ref) {
        this.ref_bijoux = ref;
        return this;
    }
    
    public int getPrix_bijoux() {
        return prix_bijoux;
    }
    
    public Bijoux setPrix_bijoux(int prix) {
        this.prix_bijoux = prix;
        return this;
    }
    
    public Designer getDesigner() {
        return designer;
    }
    
    public Bijoux setDesigner(Designer designer) {
        this.designer = designer;
        return this;
    }
    
    public Categorie getCategorie() {
        return categorie;
    }
    
    public Bijoux setCategorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public String getImage_bijoux() {
        return image_bijoux;
    }

    public Bijoux setImage_bijoux(String image) {
        this.image_bijoux = image;
        return this;
    }

    public String getDescription_bijoux() {
        return description_bijoux;
    }

    public Bijoux setDescription_bijoux(String description) {
        this.description_bijoux = description;
        return this;
    }

	public int getStock_bijoux() {
		return stock_bijoux;
	}

	public Bijoux setStock_bijoux(int stock) {
		this.stock_bijoux = stock;
		return this;
	}
    
}
