import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class SEGYTrace {
    private int SIZE_HEADER = 240;

    private ReadableByteChannel channel;
    private byte[] bytes;
    private float[] result;

    private int traceSequenceNumberWithinLine;
    private int traceSequenceNumberWithinSEGY;
    private int originalFieldRecordNumber;
    private int traceNumberWithinOriginalFieldRecord;
    private int energySourcePointNumber;
    private int ensembleNumber;
    private int traceNumberWithinEnsemble;
    private short traceIdCode;
    private short numberOfVerticallySummedTraces;
    private short numberOfHorizontallyStackedTraces;
    private short dataUse;
    private int distanceFromCenterSourcePointToCenterReceiver;
    private int elevationReceiverGroup;
    private int surfaceElevationSourceLocation;
    private int sourceDepthBelowSurface;
    private int seismicDatumElevationReceiverGroup;
    private int seismicDatumElevationSource;
    private int waterColumnHeightSourceLocation;
    private int waterColumnHeightReceiverGroup;
    private short scalarAppliedToAllElevations;
    private short scalarAppliedToAllCoord;
    private int sourceX;
    private int sourceY;
    private int groupX;
    private int groupY;
    private short coordinateUnits;
    private short weatheringVelocity;
    private short subweatheringVelocity;
    private short upholeTimeSource;
    private short upholeTimeReceiver;
    private short sourceStaticCorrection;
    private short groupStaticCorrection;
    private short totalStaticApplied;
    private short lagTimeA;
    private short lagTimeB;
    private short delayRecordingTime;
    private short muteTimeStart;
    private short muteTimeEnd;
    private short numberSamplesInThisTrace;
    private short sampleIntervalThisTrace;
    private short gainTypeFieldInstruments;
    private short instrumentGainConstant;
    private short instrumentEarlyOrInitialGain;
    private short correlated;
    private short sweepFrequencyStart;
    private short sweepFrequencyEnd;
    private short sweepLength;
    private short sweepType;
    private short sweepTraceTaperLengthStart;
    private short sweepTraceTaperLengthEnd;
    private short taperType;
    private short aliasFilterFrequency;
    private short aliasFilterSlope;
    private short notchFilterFrequency;
    private short notchFilterSlope;
    private short lowcutFrequency;
    private short highcutFrequency;
    private short lowcutSlope;
    private short highcutSlope;
    private short yearDataRecorded;
    private short dayOfYear;
    private short hourOfDay;
    private short minuteOfHour;
    private short secondOfMinute;
    private short timeBasisCode;
    private short traceWeightingFactor;
    private short geophoneGroupNumberRollSwitchPositionOne;
    private short geophoneGroupNumberTraceNumberWithOriginalFR;
    private short geophoneGroupNumberLastTraceWithOriginalFR;
    private short gapSize;
    private short overTravelAssociated;
    private int XEnsemble;
    private int YEnsemble;
    private int poststackDataInLineNumber;
    private int poststackDataCrossLineNumber;
    private int shotpointNumber;
    private short scalarAppliedToShotpoint;
    private short traceValueMeasurementUnit;
    private short transductionUnit;
    private short deviceID;
    private short scalarAppliedTimes;
    private short sourceTypeOrOrientation;
    private short sourceMeasurementUnit;

    public SEGYTrace(ReadableByteChannel channel)
    {
        this.channel = channel;
    }


    public void InitialHeader()
    {
        this.traceSequenceNumberWithinLine = Converter.ConvertByteToInt(bytes,0);
        this.traceSequenceNumberWithinSEGY = Converter.ConvertByteToInt(bytes,4);;
        this.originalFieldRecordNumber = Converter.ConvertByteToInt(bytes,8);;
        this.traceNumberWithinOriginalFieldRecord = Converter.ConvertByteToInt(bytes,12);;
        this.energySourcePointNumber = Converter.ConvertByteToInt(bytes,16);;
        this.ensembleNumber = Converter.ConvertByteToInt(bytes,20);;
        this.traceNumberWithinEnsemble = Converter.ConvertByteToInt(bytes,24);;
        this.traceIdCode = Converter.ConvertByteToShort(bytes, 28);
        this.numberOfVerticallySummedTraces = Converter.ConvertByteToShort(bytes, 30);
        this.numberOfHorizontallyStackedTraces = Converter.ConvertByteToShort(bytes, 32);
        this.dataUse = Converter.ConvertByteToShort(bytes, 34);
        this.distanceFromCenterSourcePointToCenterReceiver = Converter.ConvertByteToInt(bytes,36);
        this.elevationReceiverGroup = Converter.ConvertByteToInt(bytes,40);
        this.surfaceElevationSourceLocation = Converter.ConvertByteToInt(bytes,44);
        this.sourceDepthBelowSurface = Converter.ConvertByteToInt(bytes,48);
        this.seismicDatumElevationReceiverGroup = Converter.ConvertByteToInt(bytes,52);
        this.seismicDatumElevationSource = Converter.ConvertByteToInt(bytes,56);
        this.waterColumnHeightSourceLocation = Converter.ConvertByteToInt(bytes,60);
        this.waterColumnHeightReceiverGroup = Converter.ConvertByteToInt(bytes,64);
        this.scalarAppliedToAllElevations = Converter.ConvertByteToShort(bytes,68);
        this.scalarAppliedToAllCoord = Converter.ConvertByteToShort(bytes,70);
        this.sourceX = Converter.ConvertByteToInt(bytes,72);
        this.sourceY = Converter.ConvertByteToInt(bytes,76);
        this.groupX = Converter.ConvertByteToInt(bytes,80);
        this.groupY = Converter.ConvertByteToInt(bytes,84);
        this.coordinateUnits = Converter.ConvertByteToShort(bytes,88);
        this.weatheringVelocity = Converter.ConvertByteToShort(bytes,90);
        this.subweatheringVelocity = Converter.ConvertByteToShort(bytes,92);
        this.upholeTimeSource = Converter.ConvertByteToShort(bytes,94);
        this.upholeTimeReceiver = Converter.ConvertByteToShort(bytes,96);
        this.sourceStaticCorrection = Converter.ConvertByteToShort(bytes,98);
        this.groupStaticCorrection = Converter.ConvertByteToShort(bytes,100);
        this.totalStaticApplied = Converter.ConvertByteToShort(bytes,102);
        this.lagTimeA = Converter.ConvertByteToShort(bytes,104);
        this.lagTimeB = Converter.ConvertByteToShort(bytes,106);
        this.delayRecordingTime = Converter.ConvertByteToShort(bytes,108);
        this.muteTimeStart = Converter.ConvertByteToShort(bytes,110);
        this.muteTimeEnd = Converter.ConvertByteToShort(bytes,112);
        this.numberSamplesInThisTrace = Converter.ConvertByteToShort(bytes,114);
        this.sampleIntervalThisTrace = Converter.ConvertByteToShort(bytes,116);
        this.gainTypeFieldInstruments = Converter.ConvertByteToShort(bytes,118);
        this.instrumentGainConstant = Converter.ConvertByteToShort(bytes,120);
        this.instrumentEarlyOrInitialGain = Converter.ConvertByteToShort(bytes,122);
        this.correlated = Converter.ConvertByteToShort(bytes,124);
        this.sweepFrequencyStart = Converter.ConvertByteToShort(bytes,126);
        this.sweepFrequencyEnd = Converter.ConvertByteToShort(bytes,128);
        this.sweepLength = Converter.ConvertByteToShort(bytes,130);
        this.sweepType = Converter.ConvertByteToShort(bytes,132);
        this.sweepTraceTaperLengthStart = Converter.ConvertByteToShort(bytes,134);
        this.sweepTraceTaperLengthEnd = Converter.ConvertByteToShort(bytes,136);
        this.taperType = Converter.ConvertByteToShort(bytes,138);
        this.aliasFilterFrequency = Converter.ConvertByteToShort(bytes,140);
        this.aliasFilterSlope = Converter.ConvertByteToShort(bytes,142);
        this.notchFilterFrequency = Converter.ConvertByteToShort(bytes,144);
        this.notchFilterSlope = Converter.ConvertByteToShort(bytes,146);
        this.lowcutFrequency = Converter.ConvertByteToShort(bytes,148);
        this.highcutFrequency = Converter.ConvertByteToShort(bytes,150);
        this.lowcutSlope = Converter.ConvertByteToShort(bytes,152);
        this.highcutSlope = Converter.ConvertByteToShort(bytes,154);
        this.yearDataRecorded = Converter.ConvertByteToShort(bytes,156);
        this.dayOfYear = Converter.ConvertByteToShort(bytes,158);
        this.hourOfDay = Converter.ConvertByteToShort(bytes,160);
        this.minuteOfHour = Converter.ConvertByteToShort(bytes,162);
        this.secondOfMinute = Converter.ConvertByteToShort(bytes,164);
        this.timeBasisCode = Converter.ConvertByteToShort(bytes,166);
        this.traceWeightingFactor = Converter.ConvertByteToShort(bytes,168);
        this.geophoneGroupNumberRollSwitchPositionOne = Converter.ConvertByteToShort(bytes,170);
        this.geophoneGroupNumberTraceNumberWithOriginalFR = Converter.ConvertByteToShort(bytes,172);
        this.geophoneGroupNumberLastTraceWithOriginalFR = Converter.ConvertByteToShort(bytes,174);
        this.gapSize = Converter.ConvertByteToShort(bytes,176);
        this.overTravelAssociated = Converter.ConvertByteToShort(bytes,178);
        this.XEnsemble = Converter.ConvertByteToInt(bytes,180);
        this.YEnsemble = Converter.ConvertByteToInt(bytes,184);
        this.poststackDataInLineNumber = Converter.ConvertByteToInt(bytes,188);
        this.poststackDataCrossLineNumber = Converter.ConvertByteToInt(bytes,192);
        this.shotpointNumber = Converter.ConvertByteToInt(bytes,196);
        this.scalarAppliedToShotpoint = Converter.ConvertByteToShort(bytes,200);
        this.traceValueMeasurementUnit = Converter.ConvertByteToShort(bytes,202);
        this.transductionUnit = Converter.ConvertByteToShort(bytes,210);
        this.deviceID = Converter.ConvertByteToShort(bytes,212);
        this.scalarAppliedTimes = Converter.ConvertByteToShort(bytes,214);
        this.sourceTypeOrOrientation = Converter.ConvertByteToShort(bytes,216);
        this.sourceMeasurementUnit = Converter.ConvertByteToShort(bytes,230);
    }
    public boolean Read(short dataSampleFormatCode) {
        if (!channel.isOpen())
            return false;
        ByteBuffer traceHeader = ByteBuffer.allocate(240);
        try {
            if (channel.read(traceHeader)!=240) {
                channel.close();
                return false;
            }
            bytes = traceHeader.array();
        }
        catch(IOException e)
        {
            System.out.println("Wrong.");
        }
        InitialHeader();
        int formatSize;
        if (dataSampleFormatCode == 1 || dataSampleFormatCode==2 || dataSampleFormatCode == 4 ||  dataSampleFormatCode == 5)
            formatSize=4;
        else if (dataSampleFormatCode == 3)
            formatSize=2;
        else formatSize=1;
        int dataLength = formatSize * numberSamplesInThisTrace;
        ByteBuffer trace = ByteBuffer.allocate(dataLength);
        try {
            if (channel.read(trace) != dataLength) {
                channel.close();
                return false;
            }
        }
        catch (IOException e)
        {
            System.out.println("Wrong.");
        }
        bytes=trace.array();
        result = new float[bytes.length/4];
        for (int i=0;i<numberSamplesInThisTrace;i++)
            result[i]=ByteBuffer.wrap(bytes, 4*i, 4).getFloat();
        return true;
    }

    public String printTraceHeader() {
        return "\nTrace sequence number within line " + traceSequenceNumberWithinLine +
                "\nTrace sequence number within SEGY " + traceSequenceNumberWithinSEGY +
                "\nOriginal field record number " + originalFieldRecordNumber +
                "\nTrace number within original field record " + traceNumberWithinOriginalFieldRecord +
                "\nEnergy source point number " + energySourcePointNumber +
                "\nEnsemble number " + ensembleNumber +
                "\nTrace number within ensemble " + traceNumberWithinEnsemble +
                "\nTrace ID code " + traceIdCode +
                "\nNumber of vertically summed traces " + numberOfVerticallySummedTraces +
                "\nNumber of horizontally stacked traces " + numberOfHorizontallyStackedTraces +
                "\nData use " + dataUse +
                "\nDistance from center source point to center receiver " + distanceFromCenterSourcePointToCenterReceiver +
                "\nElevation receiver group " + elevationReceiverGroup +
                "\nSurface elevation source location " + surfaceElevationSourceLocation +
                "\nSource depth below surface " + sourceDepthBelowSurface +
                "\nSeismic datum elevation receiver group " + seismicDatumElevationReceiverGroup +
                "\nSesismic datum elevation source " + seismicDatumElevationSource +
                "\nWater column height source location " + waterColumnHeightSourceLocation +
                "\nWater column height receiver group " + waterColumnHeightReceiverGroup +
                "\nScalar applied to all elevation " + scalarAppliedToAllElevations +
                "\nScalar applied to all coordinates " + scalarAppliedToAllCoord +
                "\nSource X " + sourceX +
                "\nSource Y " + sourceY +
                "\nGroup X "  + groupX +
                "\nGroup Y " + groupY +
                "\nCoordinate units " + coordinateUnits +
                "\nWeathering velocity " + weatheringVelocity +
                "\nSubweathering velocity " + subweatheringVelocity +
                "\nUphole time source " + upholeTimeSource +
                "\nUphole time receiver " + upholeTimeReceiver +
                "\nSource static correction " + sourceStaticCorrection +
                "\nGroup static correction " + groupStaticCorrection +
                "\nTotal static applied " + totalStaticApplied +
                "\nLag time A " + lagTimeA +
                "\nLag time B " + lagTimeB +
                "\nDelay recording time " + delayRecordingTime +
                "\nMute time start " + muteTimeStart +
                "\nMute time end " + muteTimeEnd +
                "\nNumber samples in this trace " + numberSamplesInThisTrace +
                "\nSample interval this trace " + sampleIntervalThisTrace +
                "\nGain type field instrument " + gainTypeFieldInstruments +
                "\nInstrument gain constant " + instrumentGainConstant +
                "\nInstrument early or initial gain " + instrumentEarlyOrInitialGain +
                "\nCorrelated " + correlated +
                "\nSweep frequency start " + sweepFrequencyStart +
                "\nSweep frequency end " + sweepFrequencyEnd +
                "\nSweep length " + sweepLength +
                "\nSweep type " + sweepType +
                "\nSweep trace taper length start " + sweepTraceTaperLengthStart +
                "\nSweep trace taper length end " + sweepTraceTaperLengthEnd +
                "\nTaper type " + taperType +
                "\nAlias filter frequency " + aliasFilterFrequency +
                "\nAlias filter slope " + aliasFilterSlope +
                "\nNotch filter frequency " + notchFilterFrequency +
                "\nNotch filter slope " + notchFilterSlope +
                "\nLowCut frequency " + lowcutFrequency +
                "\nHightCut frequency " + highcutFrequency +
                "\nLowCut slope " + lowcutSlope +
                "\nHighCut slope " + highcutSlope +
                "\nYear data recorded " + yearDataRecorded +
                "\nDay of year " + dayOfYear +
                "\nHour of day " + hourOfDay +
                "\nMinute of hour " + minuteOfHour +
                "\nSecond of minute " + secondOfMinute +
                "\nTime basis code " + timeBasisCode +
                "\nTrace weighting factor " + traceWeightingFactor +
                "\nGeophone group number roll switch position one " + geophoneGroupNumberRollSwitchPositionOne +
                "\nGeophone group number trace number with original FR " + geophoneGroupNumberTraceNumberWithOriginalFR +
                "\nGeophone group number last trace with original FR " + geophoneGroupNumberLastTraceWithOriginalFR +
                "\nGap size " + gapSize +
                "\nOver travel Associated " + overTravelAssociated +
                "\nX ensemble " + XEnsemble +
                "\nY ensemble " + YEnsemble +
                "\nPoststack data in line number " + poststackDataInLineNumber +
                "\nPoststack data cross-line number " + poststackDataCrossLineNumber +
                "\nShotpoint number " + shotpointNumber +
                "\nScalar applied to shotpoint " + scalarAppliedToShotpoint +
                "\nTrace value measurement unit " + traceValueMeasurementUnit +
                "\nTransduction unit " + transductionUnit +
                "\nDevice ID " + deviceID +
                "\nScalar applied times" + scalarAppliedTimes +
                "\nSource type or orientation " + sourceTypeOrOrientation +
                "\nSource measurement unit " + sourceMeasurementUnit;
    }

    public String printDataTrace()
    {
        String out = "Index Value";
        for(int i=0;i<result.length;i++)
            out+= "\n" + i + " " + result[i];
        return out;
    }
}
