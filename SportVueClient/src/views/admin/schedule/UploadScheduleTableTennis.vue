<template>
  <v-form ref="form" lazy-validation>
    <h3>Set 1</h3>
    <v-row>
      <v-col>
        <v-text-field
          v-model="score1h1"
          label="Bàn thắng team 1 "
          :rules="[
            (v) => Number.isInteger(Number(v)) || 'nhập chính xác số',
            (v) => v != '' || 'Không để trống',
          ]"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="score2h1"
          label="Bàn thắng team 2"
          :rules="[
            (v) => Number.isInteger(Number(v)) || 'nhập chính xác số',
            (v) => v != '' || 'Không để trống',
          ]"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-textarea
      v-model="description1"
      name="input-7-1"
      label="Diễn biến set 1"
      required
      :rules="[(v) => v != '' || 'Không để trống']"
    ></v-textarea>
    <h3>Set 2</h3>
    <v-row>
      <v-col>
        <v-text-field
          v-model="score1h2"
          label="Bàn thắng team 1 "
          required
          :rules="[
            (v) => Number.isInteger(Number(v)) == true || 'nhập chính xác số',
            (v) => v != '' || 'Không để trống',
          ]"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="score2h2"
          label="Bàn thắng team 2"
          required
          :rules="[
            (v) => Number.isInteger(Number(v)) == true || 'nhập chính xác số',
            (v) => v != '' || 'Không để trống',
          ]"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-textarea
      v-model="description2"
      name="input-7-1"
      label="Diễn biến set 2"
      required
      :rules="[(v) => v != '' || 'Không để trống']"
    ></v-textarea>
    <h3>Set 3</h3>
    <v-row>
      <v-col>
        <v-text-field
          v-model="score1h3"
          label="Bàn thắng team 1 "
          :rules="[
            (v) => Number.isInteger(Number(v)) || 'nhập chính xác số',
            (v) => v != '' || 'Không để trống',
          ]"
        ></v-text-field>
      </v-col>
      <v-col>
        <v-text-field
          v-model="score2h3"
          label="Bàn thắng team 2"
          :rules="[
            (v) => Number.isInteger(Number(v)) || 'nhập chính xác số',
            (v) => v != '' || 'Không để trống',
          ]"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-textarea
      v-model="description1"
      name="input-7-1"
      label="Diễn biến set 3"
      required
      :rules="[(v) => v != '' || 'Không để trống']"
    ></v-textarea>
    <h3>Kết thúc</h3>
    <v-textarea
      v-model="description3"
      name="input-7-1"
      label="Đánh giá khách quan"
      required
      :rules="[(v) => v != '' || 'Không để trống']"
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
      accept="video/mp4"
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
      Upload
      <v-icon right dark> mdi-cloud-upload </v-icon>
    </v-btn>
  </v-form>
</template>
<script>
export default {
  props: {
    schedule: Object,
    callback: Function,
    loadData: Function,
  },
  data: () => ({
    score1h1: "",
    score2h1: "",
    score1h2: "",
    score2h2: "",
    score1h3: "",
    score2h3: "",
    description1: "",
    description2: "",
    description3: "",
    description4: "",

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
      if (!this.$refs.form.validate()) {
        this.$refs.form.validate();
        // console.log(this.schedule);
      } else {
        var bodyFormData = new FormData();
        var score1 = 0;
        var score2 = 0;
        if (this.score1h1 > this.score2h1) {
          score1++;
        } else {
          score2++;
        }
        if (this.score1h2 > this.score2h2) {
          score1++;
        } else {
          score2++;
        }
        if (this.score1h3 > this.score2h3) {
          score1++;
        } else {
          score2++;
        }
        bodyFormData.append("idSchedule", this.data.idSchedule);
        bodyFormData.append("scoreTeam1",score1);
        bodyFormData.append("scoreTeam2",score2);
        bodyFormData.append("idTeam1", this.data.idTeam1);
        bodyFormData.append("idTeam2", this.data.idTeam2);
        bodyFormData.append("description", this.description4);
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
                  .dispatch("round/uploadSchedule", {
                    roundName: "Set 3",
                    roundScore1: this.score1h3,
                    roundScore2: this.score2h3,
                    roundDescription: this.description3,
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
                      });
                  });
              });
          });
      }
    },
  },
};
</script>