json:
	cd app && ./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json

run-dist:
	cd app && ./build/install/app/bin/app
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
