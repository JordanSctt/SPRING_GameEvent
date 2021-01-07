package fr.greta.java;

import fr.greta.java.videogames.domain.GameGenre;
import fr.greta.java.videogames.persistence.GameEntity;
import fr.greta.java.videogames.persistence.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class VideoGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoGameApplication.class, args);
	}

	/*
	@Autowired
	private GameRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		if(repository.count() == 0) {
			save("Life is Feudal", 16, "Très bon MMO hardcore", GameGenre.MULTIJOUEUR);
			save("Conqueror's Blade", 15, "Très bon MMO avec un coté RPG", GameGenre.MULTIJOUEUR);
			save("Counter-Strike: Global Offensive", 14, "Classique du FPS en ligne", GameGenre.FPS);
			save("DayZ", 12, "Jeu de survie en ligne", GameGenre.MULTIJOUEUR);
			save("Mist Survival", 13, "Jeu de survie solo", GameGenre.AVENTURE);
			save("Mordhau", 14, "Jeu de bataille médieval", GameGenre.AVENTURE);
			save("Mount & Blade: Warband", 16, "RPG médieval très populaire", GameGenre.RPG);
			save("Night of the Dead", 13, "Tower défense zombies, coop à 4 joueurs", GameGenre.ACTION);
			save("PUBG", 15, "Battle royal très populaire", GameGenre.MULTIJOUEUR);
			save("Rust", 18, "Un des jeux de survie en ligne les plus populaire", GameGenre.MULTIJOUEUR);
			save("Scum", 18, "Jeux de survie en ligne", GameGenre.MULTIJOUEUR);
			save("Space Engineers", 13, "Simulation spacial, sandbox multijoueur", GameGenre.SIMULATION);
			save("Grand Theft Auto V", 19, "la référence des jeux open world", GameGenre.ACTION);
			save("Red Dead Redemption 2", 18, "la référence des jeux open world western", GameGenre.ACTION);
			save("Age of Empires III", 16, "Jeu de gestion/strategie populaire", GameGenre.STRATEGIE);
			save("Football Manager 2021", 15, "Jeu de gestion/strategie dans l'univers du football", GameGenre.STRATEGIE);
			save("Crusader Kings III", 17, "Jeu de strategie/simulation dans l'univers du moyen âge", GameGenre.STRATEGIE);
			save("Planet Zoo", 14, "Jeu de simulation de zoo", GameGenre.SIMULATION);
			save("Sea of Thieves", 15, "Jeu d'action naval/pirate", GameGenre.ACTION);
			save("Apex Legends", 13, "Battle royal très populaire", GameGenre.MULTIJOUEUR);
			save("Cyberpunk 2077", 8, "Déception de l'année 2020", GameGenre.AVENTURE);
			save("Destiny 2", 14, "Très bon jeu d'aventure/action", GameGenre.ACTION);
			save("Tom Clancy's Rainbow Six Siege", 13, "Jeu d'aventure, shooter en ligne", GameGenre.ACTION);
			save("The Elder Scrolls Online", 15, "Jeu MMORPG en ligne", GameGenre.RPG);
			save("Dead by Daylight", 13, "Jeu en coop, action/horreur", GameGenre.ACTION);
			save("Total War: WARHAMMER II", 16, "Jeu d'exploration dans l'unirvers Wharhammer", GameGenre.ACTION);
			save("The Forest", 16, "Jeu de survie en multijoueur/coop, horreur", GameGenre.AVENTURE);
			save("ARK: Survival Evolved", 13, "Jeu de survie en multijoueur", GameGenre.MULTIJOUEUR);
			save("Final Fantasy XIV", 14, "Jeu MMORPG, fantaisie populaire", GameGenre.MULTIJOUEUR);
			save("Dota 2", 15, "Jeu fantaisie/action très populaire", GameGenre.ACTION);
			save("Arma 3", 17, "Jeu de simulation militaire très populaire", GameGenre.SIMULATION);
			save("Elite Dangerous", 13, "Jeu de simulation/stratégie en multijoueur", GameGenre.MULTIJOUEUR);
			save("Skyrim", 17, "Jeu de RPG très populaire", GameGenre.RPG);
			save("Warhammer: Vermintide 2", 18, "Jeu d'action dans l'univers Warhammer", GameGenre.ACTION);
			save("Conan Exiles", 14, "Jeu d'action/survie multi", GameGenre.MULTIJOUEUR);
			save("The Witcher 3: Wild Hunt", 15, "Jeu d'action fantaisie en monde ouvert très populaire", GameGenre.RPG);
			save("Among Us", 16, "Jeu en multi, jeu de rôle", GameGenre.MULTIJOUEUR);
			save("Euro Truck Simulator 2", 15, "Jeu de simulation de transport routier", GameGenre.SIMULATION);
			save("No Man's Sky", 13, "Jeu d'exploration spacial", GameGenre.AVENTURE);
			save("New World", 14, "Jeu MMORPG, aventure, fantaisie", GameGenre.MULTIJOUEUR);
			save("Squad", 14, "Jeu en ligne, FPS militaire", GameGenre.MULTIJOUEUR);
			save("Cities: Skylines", 15, "Jeu de simulation de construction de ville", GameGenre.SIMULATION);
			save("Farming Simulator 19", 14, "Jeu de simulation d'agriculture", GameGenre.SIMULATION);
		}
	}

	private void save(String title, int note, String commentaire, GameGenre genre) {
		GameEntity game1 = new GameEntity();
		game1.setTitle(title);
		game1.setNote(note);
		game1.setCommentaire(commentaire);
		game1.setGenre(genre);
		repository.save(game1);
	}
	*/

}
