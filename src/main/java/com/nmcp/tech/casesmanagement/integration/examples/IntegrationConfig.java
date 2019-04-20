package com.nmcp.tech.casesmanagement.integration.examples;

import com.nmcp.tech.casesmanagement.csvbatch.JobCompletionNotificationListeners.FacilityJobCompletionNotificationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

/**
 * Created by Hamza on 2019-04-02.
 */

@Configuration
public class IntegrationConfig {

    private static final Logger log = LoggerFactory.getLogger(FacilityJobCompletionNotificationListener.class);

//    AtomicInteger integer = new AtomicInteger(new Integer(0));

    @Autowired
    private Transformer transformer;

//    @Bean
//    public IntegrationFlow getIntegrationFlow() {
//
//        return IntegrationFlows.from(fileReader(), spec ->
//                spec.poller(Pollers.fixedDelay(5000)))
//                .transform(transformer, "transform")
//                .handle(fileWriter()).get();
//    }

    @Bean
    public IntegrationFlow getIntegrationFlow() {
        return IntegrationFlows.from(fileReader(), spec ->
                spec.poller(Pollers.fixedDelay(5000)))
//                .transform(transformer, "transform")
                .handle(fileWriter()).get();
    }

//    @Bean
//    public IntegrationFlow getIntegrationFlow(FileWriterGateway gateway, AtomicInteger integer) {
//
//        return IntegrationFlows.from(integer, "getAndIncrement", spec ->
//                spec.poller(Pollers.fixedDelay(5000)))
//                .<Integer>handle(message -> {
//                    gateway.writeToFile("simple1.txt", message.getPayload().toString());
//                }).get();
//    }

    private FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("destination"));
//        handler.
        handler.setExpectReply(false);
//        handler.setFileNameGenerator(Objects::toString);
        handler.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
//        handler.setAppendNewLine(true);
        return handler;
    }

    @Bean
    public FileReadingMessageSource fileReader() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("source"));
        return source;
    }
//    @Bean
//    @InboundChannelAdapter(channel="file-channel",
//            poller=@Poller(fixedDelay="1000"))
//    public MessageSource<File> fileReadingMessageSource() {
//        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
//        sourceReader.setDirectory(new File(INPUT_DIR));
//        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
//        return sourceReader;
//    }
//When writing the equivalent file-reading inbound channel adapter in the Java DSL,
//    the inboundAdapter() method from the Files class achieves the same thing. An outbound channel adapter is the end of the line for the integration flow, handing off the
//    final message to the application or to some other system:
//@Bean
//public IntegrationFlow fileReaderFlow() {
//    return IntegrationFlows
//            .from(Files.inboundAdapter(new File(INPUT_DIR))
//                    .patternFilter(FILE_PATTERN))
//            .get();
//}
//    @Bean
//    @InboundChannelAdapter(
//            poller=@Poller(fixedRate="1000"), channel="numberChannel")
//    public MessageSource<Integer> numberSource(AtomicInteger source) {
//        return () -> {
//            return new GenericMessage<>(source.getAndIncrement());
//        };
//    }
//This @Bean method declares an inbound channel adapter bean which, per the @InboundChannelAdapter annotation, submits a number from the injected AtomicInteger to
//    the channel named numberChannel every 1 second (or 1,000 ms).
//Whereas @InboundChannelAdapter indicates an inbound channel adapter when
//    using Java configuration, the from() method is how it’s done when using the Java DSL
}
