run:
	{ \
	docker compose build ;\
	docker compose up -d ;\
    cd target ;\
    java -jar springboot-mongodb-0.0.1-SNAPSHOT.jar ;\
    }

stop:
    { \
	docker compose down ;\
    }