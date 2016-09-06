package fr.lteconsulting.client;

import java.util.Random;

public class Mots
{
	public static String mot()
	{
		return mots[new Random().nextInt( mots.length )];
	}

	public static String nom()
	{
		String mot = mot();
		return mot.substring( 0, 1 ).toUpperCase() + mot.substring( 1 ).toLowerCase();
	}

	public static String phrase()
	{
		int nbMots = 3 + new Random().nextInt( 3 );
		return phrase( nbMots );
	}

	public static String phrase( int nbMots )
	{
		String phrase = "";
		
		for( int i = 0; i < nbMots; i++ )
		{
			if( i > 0 )
				phrase += " ";

			phrase += mot();
		}

		phrase = phrase.substring( 0, 1 ).toUpperCase() + phrase.substring( 1 );

		return phrase;
	}

	private static final String[] mots = {
			"المفاتيح العربية",
			"汉语/漢語",
			"敬語は人間",
			"下関係の認",
			"བོད་ཡིག",
			"кирилиця",
			"Éole",
			"Styx",
			"གཙིག",
			"Héra",
			"Vénus",
			"Éos",
			"ဟနိ",
			"Thésée",
			"religion",
			"Pâris",
			"indo-européen",
			"Héraclès",
			"Gaïa",
			"Agamemnon",
			"Cronos",
			"thétis",
			"troyen",
			"Deucalion",
			"Olympe",
			"chimère" };
}
