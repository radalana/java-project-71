run-dist:
	cd app && app/./build/install/app/bin/app
build:
	cd app && ./gradlew build
.PHONY: build

install:
	cd app && ./gradlew clean install
run:
	cd app && ./gradlew run
lint:
	cd app && ./gradlew checkstyleMain
test:
	cd app && ./gradlew test
report:
	cd app && ./gradlew jacocoTestReport
