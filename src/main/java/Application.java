///usr/bin/env java --enable-preview --class-path ${APP_DIR:-.}/lib/'*' "$0" "$@"; exit $?

import language.api.Greeting;
import language.api.Greeting.Language;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import static picocli.CommandLine.getCommandMethods;

@Command(name = "PolyglotHello", mixinStandardHelpOptions = true)
int polyglotHello(
        @Option(names = {"-l", "--language"}, required = true, description = "Valid values are: ${COMPLETION-CANDIDATES}")
        Language language
) {
    println(Greeting.byLanguage(language).getGreeting());
    return CommandLine.ExitCode.OK;
}

void main(String[] args) {
    var method = getCommandMethods(getClass(), null).get(0);
    var commandLine = new CommandLine(method);
    int exitCode = commandLine.execute(args);
    System.exit(exitCode);
}
