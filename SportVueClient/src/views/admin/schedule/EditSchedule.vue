<template>
  <div>
    <v-form ref="form">
      <v-text-field
        disabled
        v-model="selectTournament"
        label="Tournament"
        :rules="[
          (v) => !!v || 'Item is required',
          (v) => v != '' || 'Item is required',
        ]"
      ></v-text-field>
      <v-row>
        <v-col class="d-flex" cols="12" sm="6">
          <v-autocomplete
            v-model="selectTeam1"
            :items="team"
            item-text="nameTeam"
            item-value="idTeam"
            dense
            label="Team1"
            :rules="[
              (v) => !!v || 'Item is required',
              (v) => v != '' || 'Item is required',
              (v) => v != this.selectTeam2 || 'Iteam the same selectTeam2',
            ]"
          ></v-autocomplete>
        </v-col>
        <v-col class="d-flex" cols="12" sm="6">
          <v-autocomplete
            v-model="selectTeam2"
            :items="team"
            item-text="nameTeam"
            item-value="idTeam"
            dense
            label="Team2"
            :rules="[
              (v) => !!v || 'Item is required',
              (v) => v != '' || 'Item is required',
              (v) => v != this.selectTeam1 || 'Iteam the same selectTeam1',
            ]"
          ></v-autocomplete>
        </v-col>
        <v-col>
          <v-menu
            ref="menu1"
            v-model="menu1"
            :close-on-content-click="false"
            :return-value.sync="menu1"
            transition="scale-transition"
            offset-y
            min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="timeStart"
                label="Time start"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                :rules="rulesTimeStart"
                required
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="timeStart" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="menu1 = false">
                Cancel
              </v-btn>
              <v-btn text color="primary" @click="$refs.menu1.save(timeStart)">
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
        </v-col>
        <v-col>
          <v-menu
            ref="menu2"
            v-model="menu2"
            :close-on-content-click="false"
            :return-value.sync="menu2"
            transition="scale-transition"
            offset-y
            min-width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="timeEnd"
                label="Time start"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                :rules="rulesTimeEnd"
                required
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="timeEnd" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn text color="primary" @click="menu2 = false">
                Cancel
              </v-btn>
              <v-btn text color="primary" @click="$refs.menu2.save(timeEnd)">
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>
        </v-col>
      </v-row>
      <div>
        <v-text-field
          label="Address"
          v-model="address"
          :counter="15"
          required
          :rules="[
            (v) => !!v || 'Item is required',
            (v) => v != '' || 'Item is required',
            (v) =>
              (v && v.length <= 15) || 'Name must be less than 15 characters',
          ]"
        ></v-text-field>
        <v-text-field
          label="Title"
          v-model="title"
          :counter="25"
          :rules="[
            (v) => !!v || 'Item is required',
            (v) => v != '' || 'Item is required',
            (v) =>
              (v && v.length <= 25) || 'Name must be less than 25 characters',
          ]"
        ></v-text-field>
      </div>
      <v-btn class="mx-2" dark color="blue-grey" @click="cancel">
        Reset
      </v-btn>
      <v-btn class="mx-2" dark color="primary" @click="submit"> Edit </v-btn>
    </v-form>
  </div>
</template>
<script>
var dateNow = new Date().toISOString().substr(0, 10);
import Schedule from "../../../models/schedule";

export default {
  props: {
    hideModal: {
      type: Function,
    },
    getData: {
      type: Function,
    },
    dataEdit: {
      type: Object,
    },
  },
  data: () => ({
    schedule: new Schedule(),
    tournament: [],
    selectTournament: "",
    team: [],
    selectTeam2: "",
    selectTeam1: "",
    timeStart: new Date().toISOString().substr(0, 10),
    timeEnd: new Date().toISOString().substr(0, 10),
    menu1: false,
    menu2: false,
    rulesTimeStart: [(v) => dateNow <= v || "bigger date now"],
    rulesTimeEnd: [],
    address: "",
    title: "",
  }),
  created() {
    this.reset();
  },
  methods: {
    cancel() {
      this.reset();
    },

    reset() {
      this.$store
        .dispatch("tournament/getById", this.dataEdit.idTour)
        .then((response) => {
          this.team = response.data.team;
        });
      this.selectTournament = this.dataEdit.nameTour;
      this.selectTeam2 = this.dataEdit.idTeam2;
      this.selectTeam1 = this.dataEdit.idTeam1;
      this.timeStart = this.dataEdit.timeStart;
      this.timeEnd = this.dataEdit.timeEnd;
      this.address = this.dataEdit.address;
      this.title = this.dataEdit.title;
    },
    submit() {
      if (this.$refs.form.validate() == true) {
        this.schedule.idSchedule = this.dataEdit.idSchedule;
        this.schedule.idTour = this.dataEdit.idTour;
        this.schedule.timeStart = this.timeStart;
        this.schedule.timeEnd = this.timeEnd;
        this.schedule.idTeam1 = this.selectTeam1;
        this.schedule.idTeam2 = this.selectTeam2;
        this.schedule.address = this.address;
        this.schedule.title = this.title;
        this.$store
          .dispatch("schedule/editSchedule", this.schedule)
          .then((response) => {
            if (response.data == "edit") {
              alert("Edit success");
              this.hideModal();
              this.getData();
            } else {
              alert(response.data);
            }
          });
      } else {
        this.$refs.form.validate();
      }
    },
  },
  watch: {
    timeStart() {
      this.rulesTimeEnd = [(v) => v > this.timeStart || "bigger time start"];
    },
  },
};
</script>