# According to My Calculations (Java)
<img width="681" height="106" alt="image" src="https://github.com/user-attachments/assets/995070ed-176c-4dce-9b7d-b95337fbfc0b" />
<img width="136" height="111" alt="image" src="https://github.com/user-attachments/assets/f3a95d64-8388-4ead-8609-a07f8030bce4" />

You can find the instructions for this lab at [More Than Equations][more-than-equations]. Create a new repository on GitHub to house your code. Be sure to make the repository public so that I can view and grade it.

We will use [Gradle][gradle] to automate common development tasks, [JUnit][junit] for unit testing, and [JMH][jmh] for benchmarking.

## Building the App

You can build the app using:

```bash
./gradlew build
```

## Testing the App

You can run the automated suite of tests using:

```bash
./gradlew test
```

## Running the App

You can run the app using:

```bash
./gradlew run --quiet --console=plain
```

The two flags passed to the `run` command hide the noisy output from Gradle. You can see the details from Gradle by omitting those flags.

## Running Benchmarks

We will not be benchmarking any code in this lab. Thus, you do not need to worry about running jmh.

[gradle]: https://gradle.org/
[jmh]: https://github.com/openjdk/jmh
[junit]: https://junit.org/
[more-than-equations]: https://morethanequations.com/Computer-Science/Labs/According-to-My-Calculations
