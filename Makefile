.PHONY: insert-cart-items insert-pricing-rules docker-up build-and-run

MONGO_SCRIPTS_DIR := mongo-scripts
SCRIPTS := $(MONGO_SCRIPTS_DIR)/insert-cart-items.sh $(MONGO_SCRIPTS_DIR)/insert-pricing-rules.sh

# Add a target to set execute permissions
set-permissions:
	chmod +x $(SCRIPTS)

insert-cart-items:
	./$(MONGO_SCRIPTS_DIR)/insert-cart-items.sh

insert-pricing-rules:
	./$(MONGO_SCRIPTS_DIR)/insert-pricing-rules.sh

docker-up: set-permissions insert-cart-items insert-pricing-rules
	docker-compose up -d

build-and-run: docker-up
	./gradlew clean build
	java -jar build/libs/checkout-service-1.0-SNAPSHOT.jar


