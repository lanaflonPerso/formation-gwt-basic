package fr.lteconsulting.shared;

import java.io.Serializable;
import java.util.Date;

public class Personne implements Serializable
{
	private static final long serialVersionUID = -9009571766643019434L;

	private String id;

	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String motDePasse;
	private Sexe sexe;
	private boolean accepteMarketing;
	private double latitude;
	private double longitude;

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom( String nom )
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom( String prenom )
	{
		this.prenom = prenom;
	}

	public Date getDateNaissance()
	{
		return dateNaissance;
	}

	public void setDateNaissance( Date dateNaissance )
	{
		this.dateNaissance = dateNaissance;
	}

	public String getMotDePasse()
	{
		return motDePasse;
	}

	public void setMotDePasse( String motDePasse )
	{
		this.motDePasse = motDePasse;
	}

	public Sexe getSexe()
	{
		return sexe;
	}

	public void setSexe( Sexe sexe )
	{
		this.sexe = sexe;
	}

	public boolean isAccepteMarketing()
	{
		return accepteMarketing;
	}

	public void setAccepteMarketing( boolean accepteMarketing )
	{
		this.accepteMarketing = accepteMarketing;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude( double latitude )
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude( double longitude )
	{
		this.longitude = longitude;
	}

	@Override
	public String toString()
	{
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", motDePasse=" + motDePasse + ", sexe=" + sexe + ", accepteMarketing=" + accepteMarketing + "]";
	}
}
