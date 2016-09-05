package fr.lteconsulting.shared;

import java.util.Date;

public class Personne
{
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String motDePasse;
	private Sexe sexe;
	private boolean accepteMarketing;

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

	@Override
	public String toString()
	{
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", motDePasse=" + motDePasse + ", sexe=" + sexe + ", accepteMarketing=" + accepteMarketing + "]";
	}
}
