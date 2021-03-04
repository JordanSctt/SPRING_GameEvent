package fr.greta.java;

import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.game.persistence.repository.GameRepository;
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


	@Autowired
	private GameRepository repository;


	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		if(repository.count() == 0) {
			save("Life is Feudal", "Très bon MMO hardcore");
			save("Conqueror's Blade", "Très bon MMO avec un coté RPG");
			save("Counter-Strike: Global Offensive", "Classique du FPS en ligne");
			save("DayZ", "Jeu de survie en ligne");
			save("Mist Survival", "Jeu de survie solo");
			save("Mordhau", "Jeu de bataille médieval");
			save("Mount & Blade: Warband", "RPG médieval très populaire");
			save("Night of the Dead", "Tower défense zombies, coop à 4 joueurs");
			save("PUBG", "Battle royal très populaire");
			save("Rust", "Un des jeux de survie en ligne les plus populaire");
			save("Scum", "Jeux de survie en ligne");
			save("Space Engineers", "Simulation spacial, sandbox multijoueur");
			save("Grand Theft Auto V", "la référence des jeux open world");
			save("Red Dead Redemption 2", "la référence des jeux open world western");
			save("Age of Empires III", "Jeu de gestion/strategie populaire");
			save("Football Manager 2021", "Jeu de gestion/strategie dans l'univers du football");
			save("Crusader Kings III", "Jeu de strategie/simulation dans l'univers du moyen âge");
			save("Planet Zoo", "Jeu de simulation de zoo");
			save("Sea of Thieves", "Jeu d'action naval/pirate");
			save("Apex Legends", "Battle royal très populaire");
			save("Cyberpunk 2077", "Déception de l'année 2020");
			save("Destiny 2", "Très bon jeu d'aventure/action");
			save("Tom Clancy's Rainbow Six Siege", "Jeu d'aventure, shooter en ligne");
			save("The Elder Scrolls Online", "Jeu MMORPG en ligne");
			save("Dead by Daylight", "Jeu en coop, action/horreur");
			save("Total War: WARHAMMER II", "Jeu d'exploration dans l'unirvers Wharhammer");
			save("The Forest", "Jeu de survie en multijoueur/coop, horreur");
			save("ARK: Survival Evolved", "Jeu de survie en multijoueur");
			save("Final Fantasy XIV", "Jeu MMORPG, fantaisie populaire");
			save("Dota 2", "Jeu fantaisie/action très populaire");
			save("Arma 3", "Jeu de simulation militaire très populaire");
			save("Elite Dangerous", "Jeu de simulation/stratégie en multijoueur");
			save("Skyrim", "Jeu de RPG très populaire");
			save("Warhammer: Vermintide 2", "Jeu d'action dans l'univers Warhammer");
			save("Conan Exiles", "Jeu d'action/survie multi");
			save("The Witcher 3: Wild Hunt", "Jeu d'action fantaisie en monde ouvert très populaire");
			save("Among Us", "Jeu en multi, jeu de rôle");
			save("Euro Truck Simulator 2", "Jeu de simulation de transport routier");
			save("No Man's Sky",  "Jeu d'exploration spacial");
			save("New World", "Jeu MMORPG, aventure, fantaisie");
			save("Squad", "Jeu en ligne, FPS militaire");
			save("Cities: Skylines", "Jeu de simulation de construction de ville");
			save("Farming Simulator 19", "Jeu de simulation d'agriculture");
		}
	}

	private void save(String title,  String genre) {
		GameEntity game1 = new GameEntity();
		game1.setTitre(title);
		game1.setGenre(genre);
		repository.save(game1);
	}




}
