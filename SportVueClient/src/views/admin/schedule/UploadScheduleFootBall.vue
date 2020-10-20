<template>
  <b-overlay
    :show="busy"
    rounded
    opacity="0.6"
    spinner-small
    spinner-variant="primary"
  >
    <v-form ref="form" lazy-validation>
      <h3>Set 1</h3>
      <v-row>
        <v-col>
          <v-text-field
            v-model="score1h1"
            label="Score Team 1 "
            :rules="[
              (v) => Number.isInteger(Number(v)) || 'Enter the correct number',
              (v) => v != '' || 'Do not leave blank',
            ]"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            v-model="score2h1"
            label="Score Team 2"
            :rules="[
              (v) => Number.isInteger(Number(v)) || 'Enter the correct number',
              (v) => v != '' || 'Do not leave blank',
            ]"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-textarea
        v-model="description1"
        name="input-7-1"
        label="Description set 1"
        required
        :rules="[(v) => v != '' || 'Do not leave blank']"
      ></v-textarea>
      <h3>Set 2</h3>
      <v-row>
        <v-col>
          <v-text-field
            v-model="score1h2"
            label="Score Team 1 "
            required
            :rules="[
              (v) =>
                Number.isInteger(Number(v)) == true ||
                'Enter the correct number',
              (v) => v != '' || 'Do not leave blank',
            ]"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            v-model="score2h2"
            label="Score Team 2"
            required
            :rules="[
              (v) =>
                Number.isInteger(Number(v)) == true ||
                'Enter the correct number',
              (v) => v != '' || 'Do not leave blank',
            ]"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-textarea
        v-model="description2"
        name="input-7-1"
        label="Description set 2"
        required
        :rules="[(v) => v != '' || 'Do not leave blank']"
      ></v-textarea>
      <h3>Summary</h3>
      <v-textarea
        v-model="description3"
        name="input-7-1"
        label="Objective assessment"
        required
        :rules="[(v) => v != '' || 'Do not leave blank']"
      ></v-textarea>
      <v-file-input
        v-model="fileImage"
        accept="image/png, image/jpeg, image/bmp"
        :placeholder="'Pick an image'"
        prepend-icon="mdi-camera"
        label="Image"
        :rules="[(v) => !!v.name || 'Item is required']"
      ></v-file-input>
      <v-file-input
        v-model="fileVideo"
        accept="video/mp4,video/mov"
        :placeholder="'Pick an video'"
        prepend-icon="mdi-video"
        label="Video"
        :rules="[(v) => !!v.name || 'Item is required']"
      ></v-file-input>
      <v-btn color="blue-grey" class="ma-2 white--text" @click="cancel">
        Cancel
        <v-icon right dark> mdi-cancel </v-icon>
      </v-btn>
      <v-btn color="blue-grey" class="ma-2 white--text" @click="submit">
        Update
        <v-icon right dark> mdi-cloud-upload </v-icon>
      </v-btn>
    </v-form>
  </b-overlay>
</template>
<script>
export default {
  props: {
    schedule: Object,
    callback: Function,
    loadData: Function,
  },
  data: () => ({
    busy: false,
    score1h1: "",
    score2h1: "",
    score1h2: "",
    score2h2: "",
    description1: "",
    description2: "",
    description3: "",
    fileVideo: [],
    fileImage: [],
    data: {},
  }),
  created() {
    this.data = this.schedule;
  },
  methods: {
    reset() {
      this.$refs.form.reset();
    },
    cancel() {
      this.reset();
      this.callback();
    },
    submit() {
      this.busy = true;
      if (!this.$refs.form.validate()) {
        this.$refs.form.validate();
        this.busy = false;
      } else {
        var bodyFormData = new FormData();
        bodyFormData.append("idSchedule", this.data.idSchedule);
        bodyFormData.append(
          "scoreTeam1",
          Number(this.score1h1) + Number(this.score1h2)
        );
        bodyFormData.append(
          "scoreTeam2",
          Number(this.score2h1) + Number(this.score2h2)
        );
        bodyFormData.append("idTeam1", this.data.idTeam1);
        bodyFormData.append("idTeam2", this.data.idTeam2);
        bodyFormData.append("description", this.description3);
        bodyFormData.append("fileImage", this.fileImage);
        bodyFormData.append("fileVideo", this.fileVideo);

        this.$store
          .dispatch("round/uploadSchedule", {
            roundName: "Set 1",
            roundScore1: this.score1h1,
            roundScore2: this.score2h1,
            roundDescription: this.description1,
            roundSchedule: this.data.idSchedule,
          })
          .then((response) => {
            this.$store
              .dispatch("round/uploadSchedule", {
                roundName: "Set 2",
                roundScore1: this.score1h2,
                roundScore2: this.score2h2,
                roundDescription: this.description2,
                roundSchedule: this.data.idSchedule,
              })
              .then((response) => {
                this.$store
                  .dispatch("schedule/updateSchedule", bodyFormData)
                  .then((response) => {
                    alert(response.data);
                    this.reset();
                    this.callback();
                    this.loadData();
                    this.busy = false;
                  });
              });
          });
      }
    },
  },
};
</script>