import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;

public class SEGYBinHeader {
    private int SIZE = 400;
    private byte[] bytes;

    private int jobId;
    private int lineNumber;
    private int reelNumber;
    private short numberDataTracesPerEnsemble;
    private short numberAuxiliaryTracesPerEnsemble;
    private short sampleInterval;
    private short sampleIntervalOriginalFieldRecording;
    private short numberSamplesPerDataTrace;
    private short numberSamplesPerDataTraceOFR;
    private short dataSampleFormatCode;
    private short ensembleFold;
    private short traceSortingCode;
    private short verticalSumCode;
    private short sweepFrequencyAtStart;
    private short sweepFrequencyAtEnd;
    private short sweepLength;
    private short sweepTypeCode;
    private short traceNumberOfSweepChannel;
    private short sweepTraceTaperLengthAtStart;
    private short sweepTraceTaperLengthAtEnd;
    private short taperType;
    private short correlatedDataTraces;
    private short binaryGainRecovered;
    private short amplitudeRecoveryMethod;
    private short measurementSystem;
    private short impulseSignalPolarity;
    private short vibratoryPolarityCode;
    //If nonzero overrides numberDataTracesPerEnsemble
    private int extNumberDataTracesPerEnsemble;
    //If nonzero overrides numberAuxiliaryTracesPerEnsemble
    private int extNumberOfAuxiliaryTracesPerEnsemble;
    //If nonzero overrides numberSamplesPerDataTrace
    private int extNumberSamplesPerDataTrace;
    //If nonzero overrides sampleInterval
    private long extSampleInterval;
    //If nonzero overrides sampleIntervalOriginalFieldRecording
    private long extSampleIntervalOFR;
    //If nonzero overrides numberSamplePerDataTraceOFR;
    private int extNumberSamplesPerDataTraceOFR;
    //If nonzero overrides ensembleFold
    private int extEnsembleFold;
    private int byteOrdering;
    private short revisionNumber;
    private short fixedLengthTraceFlag;
    private int maximumNumberAdditionalTraceHeader;
    private short timeBasicsCode;
    private long numberTracesInThisFile;
    private long byteOffset;
    private int numberDataTrailerStanzaRecords;

    public SEGYBinHeader(ReadableByteChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        try{
            channel.read(buffer);
            bytes=buffer.array();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    public void Read()
    {
        this.jobId = Converter.ConvertByteToInt(bytes, 0);
        this.lineNumber = Converter.ConvertByteToInt(bytes, 4);
        this.reelNumber = Converter.ConvertByteToInt(bytes, 8);
        this.numberDataTracesPerEnsemble = Converter.ConvertByteToShort(bytes, 12);
        this.numberAuxiliaryTracesPerEnsemble = Converter.ConvertByteToShort(bytes, 14);
        this.sampleInterval = Converter.ConvertByteToShort(bytes, 16);
        this.sampleIntervalOriginalFieldRecording = Converter.ConvertByteToShort(bytes, 18) ;
        this.numberSamplesPerDataTrace = Converter.ConvertByteToShort(bytes, 20);
        this.numberSamplesPerDataTraceOFR = Converter.ConvertByteToShort(bytes, 22) ;
        this.dataSampleFormatCode = Converter.ConvertByteToShort(bytes, 24);
        this.ensembleFold = Converter.ConvertByteToShort(bytes, 26);
        this.traceSortingCode = Converter.ConvertByteToShort(bytes, 28);
        this.verticalSumCode = Converter.ConvertByteToShort(bytes, 30) ;
        this.sweepFrequencyAtStart = Converter.ConvertByteToShort(bytes, 32) ;
        this.sweepFrequencyAtEnd = Converter.ConvertByteToShort(bytes, 34);
        this.sweepLength = Converter.ConvertByteToShort(bytes, 36);
        this.sweepTypeCode = Converter.ConvertByteToShort(bytes, 38);
        this.traceNumberOfSweepChannel = Converter.ConvertByteToShort(bytes, 40);
        this.sweepTraceTaperLengthAtStart = Converter.ConvertByteToShort(bytes, 42);
        this.sweepTraceTaperLengthAtEnd = Converter.ConvertByteToShort(bytes, 44);
        this.taperType = Converter.ConvertByteToShort(bytes, 46);
        this.correlatedDataTraces = Converter.ConvertByteToShort(bytes, 48);
        this.binaryGainRecovered = Converter.ConvertByteToShort(bytes, 50);
        this.amplitudeRecoveryMethod = Converter.ConvertByteToShort(bytes, 52);
        this.measurementSystem = Converter.ConvertByteToShort(bytes, 54);
        this.impulseSignalPolarity = Converter.ConvertByteToShort(bytes, 56);
        this.vibratoryPolarityCode = Converter.ConvertByteToShort(bytes, 58) ;
        this.extNumberDataTracesPerEnsemble = Converter.ConvertByteToInt(bytes,60 );
        this.extNumberOfAuxiliaryTracesPerEnsemble = Converter.ConvertByteToInt(bytes, 64);
        this.extNumberSamplesPerDataTrace = Converter.ConvertByteToInt(bytes, 68);
        this.extSampleInterval = Converter.ConvertByteToLong(bytes, 72);
        this.extSampleIntervalOFR = Converter.ConvertByteToLong(bytes, 80);
        this.extNumberSamplesPerDataTraceOFR = Converter.ConvertByteToInt(bytes, 88);
        this.extEnsembleFold = Converter.ConvertByteToInt(bytes, 92);
        this.byteOrdering = Converter.ConvertByteToInt(bytes, 96);
        this.revisionNumber = Converter.ConvertByteToShort(bytes, 300);
        this.fixedLengthTraceFlag = Converter.ConvertByteToShort(bytes, 302);
        this.maximumNumberAdditionalTraceHeader = Converter.ConvertByteToInt(bytes, 306);
        this.timeBasicsCode = Converter.ConvertByteToShort(bytes, 310);
        this.numberTracesInThisFile = Converter.ConvertByteToLong(bytes, 312);
        this.byteOffset = Converter.ConvertByteToLong(bytes, 320);
        this.numberDataTrailerStanzaRecords = Converter.ConvertByteToInt(bytes, 328);

    }
    public String GetOutPrint() {
        return "\nJobId " + jobId +
                "\nLine number " + lineNumber +
                "\nReel number " + reelNumber +
                "\nNumber Data Traces Per Ensemble " + numberDataTracesPerEnsemble +
                "\nNumber auxiliary Traces Per Ensemble " + numberAuxiliaryTracesPerEnsemble +
                "\nSample interval " + sampleInterval +
                "\nSample interval original field recording " + sampleIntervalOriginalFieldRecording +
                "\nNumber samples per data trace " + numberSamplesPerDataTrace +
                "\nNumber samples per data trace OFR  " + numberSamplesPerDataTraceOFR +
                "\nData sample format code  " + dataSampleFormatCode +
                "\nEnsemble fold " + ensembleFold +
                "\nTrace sorting code " + traceSortingCode +
                "\nVertical sum code " + verticalSumCode +
                "\nSweep frequency at start " + sweepFrequencyAtStart +
                "\nSweep frequency at end " + sweepFrequencyAtEnd +
                "\nSweep length " + sweepLength +
                "\nSweep type code " + sweepTypeCode +
                "\nTrace number of sweep channel " + traceNumberOfSweepChannel +
                "\nSweep trace taper length at start " + sweepFrequencyAtStart +
                "\nSweep trace taper length at end " + sweepFrequencyAtEnd +
                "\nTaper type " + taperType +
                "\nCorrelated data traces " + correlatedDataTraces +
                "\nBinary gain recovered " + binaryGainRecovered +
                "\nAmplitude recovery method " + amplitudeRecoveryMethod +
                "\nMeasurement system " + measurementSystem +
                "\nImpulse signal priority " + impulseSignalPolarity +
                "\nVibratory polarity code " + vibratoryPolarityCode +
                "\nExt number data traces per ensemble " + extNumberDataTracesPerEnsemble +
                "\nExt Number auxiliary Traces Per Ensemble " + extNumberOfAuxiliaryTracesPerEnsemble +
                "\nExt Number samples per data trace " + extNumberSamplesPerDataTrace +
                "\nExt sample interval " + extSampleInterval +
                "\nExt sample interval OFR " + extSampleIntervalOFR +
                "\nExt Number samples per data trace OFR " + extNumberSamplesPerDataTraceOFR +
                "\nExt ensemble fold " + extEnsembleFold +
                "\nByte ordering " + byteOrdering +
                "\nRevision number " + revisionNumber +
                "\nFixed length trace flag " + fixedLengthTraceFlag +
                "\nMaximum number additional trace header " + maximumNumberAdditionalTraceHeader +
                "\nTime basics code " + timeBasicsCode +
                "\nNumber traces in this file " + numberTracesInThisFile +
                "\nByte offset " + byteOffset +
                "\nNumber data trailer stanza records " + numberDataTrailerStanzaRecords;
    }

    public int getExtNumberDataTracesPerEnsemble() {
        return extNumberDataTracesPerEnsemble;
    }

    public void setExtNumberDataTracesPerEnsemble(int extNumberDataTracesPerEnsemble) {
        this.extNumberDataTracesPerEnsemble = extNumberDataTracesPerEnsemble;
    }

    public int getExtNumberOfAuxiliaryTracesPerEnsemble() {
        return extNumberOfAuxiliaryTracesPerEnsemble;
    }

    public void setExtNumberOfAuxiliaryTracesPerEnsemble(int extNumberOfAuxiliaryTracesPerEnsemble) {
        this.extNumberOfAuxiliaryTracesPerEnsemble = extNumberOfAuxiliaryTracesPerEnsemble;
    }

    public int getExtNumberSamplesPerDataTrace() {
        return extNumberSamplesPerDataTrace;
    }

    public void setExtNumberSamplesPerDataTrace(int extNumberSamplesPerDataTrace) {
        this.extNumberSamplesPerDataTrace = extNumberSamplesPerDataTrace;
    }

    public long getExtSampleInterval() {
        return extSampleInterval;
    }

    public void setExtSampleInterval(long extSampleInterval) {
        this.extSampleInterval = extSampleInterval;
    }

    public long getExtSampleIntervalOFR() {
        return extSampleIntervalOFR;
    }

    public void setExtSampleIntervalOFR(long extSampleIntervalOFR) {
        this.extSampleIntervalOFR = extSampleIntervalOFR;
    }

    public int getExtNumberSamplesPerDataTraceOFR() {
        return extNumberSamplesPerDataTraceOFR;
    }

    public void setExtNumberSamplesPerDataTraceOFR(int extNumberSamplesPerDataTraceOFR) {
        this.extNumberSamplesPerDataTraceOFR = extNumberSamplesPerDataTraceOFR;
    }

    public int getExtEnsembleFold() {
        return extEnsembleFold;
    }

    public void setExtEnsembleFold(int extEnsembleFold) {
        this.extEnsembleFold = extEnsembleFold;
    }

    public int getByteOrdering() {
        return byteOrdering;
    }

    public void setByteOrdering(int byteOrdering) {
        this.byteOrdering = byteOrdering;
    }

    public short getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(short revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public short getFixedLengthTraceFlag() {
        return fixedLengthTraceFlag;
    }

    public void setFixedLengthTraceFlag(short fixedLengthTraceFlag) {
        this.fixedLengthTraceFlag = fixedLengthTraceFlag;
    }

    public int getMaximumNumberAdditionalTraceHeader() {
        return maximumNumberAdditionalTraceHeader;
    }

    public void setMaximumNumberAdditionalTraceHeader(int maximumNumberAdditionalTraceHeader) {
        this.maximumNumberAdditionalTraceHeader = maximumNumberAdditionalTraceHeader;
    }

    public short getTimeBasicsCode() {
        return timeBasicsCode;
    }

    public void setTimeBasicsCode(short timeBasicsCode) {
        this.timeBasicsCode = timeBasicsCode;
    }

    public long getNumberTracesInThisFile() {
        return numberTracesInThisFile;
    }

    public void setNumberTracesInThisFile(long numberTracesInThisFile) {
        this.numberTracesInThisFile = numberTracesInThisFile;
    }

    public long getByteOffset() {
        return byteOffset;
    }

    public void setByteOffset(long byteOffset) {
        this.byteOffset = byteOffset;
    }

    public int getNumberDataTrailerStanzaRecords() {
        return numberDataTrailerStanzaRecords;
    }

    public void setNumberDataTrailerStanzaRecords(int numberDataTrailerStanzaRecords) {
        this.numberDataTrailerStanzaRecords = numberDataTrailerStanzaRecords;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getReelNumber() {
        return reelNumber;
    }

    public void setReelNumber(int reelNumber) {
        this.reelNumber = reelNumber;
    }

    public short getNumberDataTracesPerEnsemble() {
        return numberDataTracesPerEnsemble;
    }

    public void setNumberDataTracesPerEnsemble(short numberDataTracesPerEnsemble) {
        this.numberDataTracesPerEnsemble = numberDataTracesPerEnsemble;
    }

    public short getNumberAuxiliaryTracesPerEnsemble() {
        return numberAuxiliaryTracesPerEnsemble;
    }

    public void setNumberAuxiliaryTracesPerEnsemble(short numberAuxiliaryTracesPerEnsemble) {
        this.numberAuxiliaryTracesPerEnsemble = numberAuxiliaryTracesPerEnsemble;
    }

    public short getSampleInterval() {
        return sampleInterval;
    }

    public void setSampleInterval(short sampleInterval) {
        this.sampleInterval = sampleInterval;
    }

    public short getSampleIntervalOriginalFieldRecording() {
        return sampleIntervalOriginalFieldRecording;
    }

    public void setSampleIntervalOriginalFieldRecording(short sampleIntervalOriginalFieldRecording) {
        this.sampleIntervalOriginalFieldRecording = sampleIntervalOriginalFieldRecording;
    }

    public short getNumberSamplesPerDataTrace() {
        return numberSamplesPerDataTrace;
    }

    public void setNumberSamplesPerDataTrace(short numberSamplesPerDataTrace) {
        this.numberSamplesPerDataTrace = numberSamplesPerDataTrace;
    }

    public short getNumberSamplesPerDataTraceOFR() {
        return numberSamplesPerDataTraceOFR;
    }

    public void setNumberSamplesPerDataTraceOFR(short numberSamplesPerDataTraceOFR) {
        this.numberSamplesPerDataTraceOFR = numberSamplesPerDataTraceOFR;
    }

    public short getDataSampleFormatCode() {
        return dataSampleFormatCode;
    }

    public void setDataSampleFormatCode(short dataSampleFormatCode) {
        this.dataSampleFormatCode = dataSampleFormatCode;
    }

    public short getEnsembleFold() {
        return ensembleFold;
    }

    public void setEnsembleFold(short ensembleFold) {
        this.ensembleFold = ensembleFold;
    }

    public short getTraceSortingCode() {
        return traceSortingCode;
    }

    public void setTraceSortingCode(short traceSortingCode) {
        this.traceSortingCode = traceSortingCode;
    }

    public short getVerticalSumCode() {
        return verticalSumCode;
    }

    public void setVerticalSumCode(short verticalSumCode) {
        this.verticalSumCode = verticalSumCode;
    }

    public short getSweepFrequencyAtStart() {
        return sweepFrequencyAtStart;
    }

    public void setSweepFrequencyAtStart(short sweepFrequencyAtStart) {
        this.sweepFrequencyAtStart = sweepFrequencyAtStart;
    }

    public short getSweepFrequencyAtEnd() {
        return sweepFrequencyAtEnd;
    }

    public void setSweepFrequencyAtEnd(short sweepFrequencyAtEnd) {
        this.sweepFrequencyAtEnd = sweepFrequencyAtEnd;
    }

    public short getSweepLength() {
        return sweepLength;
    }

    public void setSweepLength(short sweepLength) {
        this.sweepLength = sweepLength;
    }

    public short getSweepTypeCode() {
        return sweepTypeCode;
    }

    public void setSweepTypeCode(short sweepTypeCode) {
        this.sweepTypeCode = sweepTypeCode;
    }

    public short getTraceNumberOfSweepChannel() {
        return traceNumberOfSweepChannel;
    }

    public void setTraceNumberOfSweepChannel(short traceNumberOfSweepChannel) {
        this.traceNumberOfSweepChannel = traceNumberOfSweepChannel;
    }

    public short getSweepTraceTaperLengthAtStart() {
        return sweepTraceTaperLengthAtStart;
    }

    public void setSweepTraceTaperLengthAtStart(short sweepTraceTaperLengthAtStart) {
        this.sweepTraceTaperLengthAtStart = sweepTraceTaperLengthAtStart;
    }

    public short getSweepTraceTaperLengthAtEnd() {
        return sweepTraceTaperLengthAtEnd;
    }

    public void setSweepTraceTaperLengthAtEnd(short sweepTraceTaperLengthAtEnd) {
        this.sweepTraceTaperLengthAtEnd = sweepTraceTaperLengthAtEnd;
    }

    public short getTaperType() {
        return taperType;
    }

    public void setTaperType(short taperType) {
        this.taperType = taperType;
    }

    public short getCorrelatedDataTraces() {
        return correlatedDataTraces;
    }

    public void setCorrelatedDataTraces(short correlatedDataTraces) {
        this.correlatedDataTraces = correlatedDataTraces;
    }

    public short getBinaryGainRecovered() {
        return binaryGainRecovered;
    }

    public void setBinaryGainRecovered(short binaryGainRecovered) {
        this.binaryGainRecovered = binaryGainRecovered;
    }

    public short getAmplitudeRecoveryMethod() {
        return amplitudeRecoveryMethod;
    }

    public void setAmplitudeRecoveryMethod(short amplitudeRecoveryMethod) {
        this.amplitudeRecoveryMethod = amplitudeRecoveryMethod;
    }

    public short getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(short measurementSystem) {
        this.measurementSystem = measurementSystem;
    }

    public short getImpulseSignalPolarity() {
        return impulseSignalPolarity;
    }

    public void setImpulseSignalPolarity(short impulseSignalPolarity) {
        this.impulseSignalPolarity = impulseSignalPolarity;
    }

    public short getVibratoryPolarityCode() {
        return vibratoryPolarityCode;
    }

    public void setVibratoryPolarityCode(short vibratoryPolarityCode) {
        this.vibratoryPolarityCode = vibratoryPolarityCode;
    }
}
