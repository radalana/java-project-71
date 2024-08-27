run-dist:
	./build/install/app/bin/app files/file1.json files/file2.json
build:
	./gradlew build
.PHONY: build

install:
	./gradlew clean install
run:
	./gradlew run
lint:
	./gradlew checkstyleMain
test:
	./gradlew test
report:
	./gradlew jacocoTestReport
