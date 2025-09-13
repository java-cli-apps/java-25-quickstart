.DEFAULT_GOAL := help

package: ## Construire le livrable de l'application dans un fichier .zip
	APP_NAME=$(APP_NAME) ./gradlew scriptsDistZip

install: .check-install-dir ## Installer l'application
	unzip -q -d $(DEST_DIR) $(BUILD)/distributions/$(APP_NAME).zip

run-app: ## Lancer l'application localement
	./gradlew run --args="--language French"

run-installed-app: .check-install-dir ## Lancer l'application installée
	PATH=$(DEST_DIR)/$(APP_DIR)/bin:$(PATH) $(APP_NAME).sh --language French

test: ## Exécuter les tests
	./gradlew test

clean: ## Nettoyer le répertoire de construction
	rm --force --recursive $(BUILD)

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; { printf("\033[36m%-30s\033[0m %s\n", $$1, $$2) }'

.check-install-dir:
ifndef DEST_DIR
	$(error Please set the installation directory, for example DEST_DIR=~ make install)
endif

APP_NAME ?= QuickStart
APP_DIR := $(APP_NAME)
BUILD := build
